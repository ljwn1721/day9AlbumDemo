package est.day9AlbumDemo.service;

import est.day9AlbumDemo.domain.Album;
import est.day9AlbumDemo.domain.Photo;
import est.day9AlbumDemo.domain.User;
import est.day9AlbumDemo.dto.imports.AlbumImportDto;
import est.day9AlbumDemo.dto.imports.PhotoImportDto;
import est.day9AlbumDemo.dto.imports.UserImportDto;
import est.day9AlbumDemo.repository.AlbumRepository;
import est.day9AlbumDemo.repository.PhotoRepository;
import est.day9AlbumDemo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class DataImportService {

    private final UserRepository userRepository;
    private final AlbumRepository albumRepository;
    private final PhotoRepository photoRepository;
    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * User 데이터 import
     */
    public void importUsers() {
        String url = "https://jsonplaceholder.typicode.com/users";
        UserImportDto[] users = restTemplate.getForObject(url, UserImportDto[].class);

        if (users != null) {
            Arrays.stream(users).forEach(dto -> {
                User user = User.builder()
                        .externalId(dto.getId())
                        .name(dto.getName())
                        .email(dto.getEmail())
                        .phone(dto.getPhone())
                        .company(dto.getCompany().getName())
                        .city(dto.getAddress().getCity())
                        .password(passwordEncoder.encode("1234"))
                        .build();
                userRepository.save(user);
            });
        }
    }

    /**
     * Album 데이터 import
     */
    public void importAlbums() {
        String url = "https://jsonplaceholder.typicode.com/albums";
        AlbumImportDto[] albums = restTemplate.getForObject(url, AlbumImportDto[].class);

        if (albums != null) {
            Arrays.stream(albums).forEach(dto -> {
                User user = userRepository.findById(dto.getUserId())
                        .orElse(null); // 없는 User는 skip
                if (user != null) {
                    Album album = Album.builder()
                            .externalId(dto.getId())
                            .title(dto.getTitle())
                            .user(user)
                            .build();
                    albumRepository.save(album);
                }
            });
        }
    }

    /**
     * Photo 데이터 import
     */
    public void importPhotos() {
        String url = "https://jsonplaceholder.typicode.com/photos";
        PhotoImportDto[] photos = restTemplate.getForObject(url, PhotoImportDto[].class);

        if (photos != null) {
            Arrays.stream(photos).forEach(dto -> {
                Album album = albumRepository.findById(dto.getAlbumId())
                        .orElse(null); // 없는 Album은 skip
                if (album != null) {
                    Photo photo = Photo.builder()
                            .externalId(dto.getId())
                            .album(album)
                            .thumbnail(dto.getThumbnail())
                            .build();
                    photoRepository.save(photo);
                }
            });
        }
    }
}