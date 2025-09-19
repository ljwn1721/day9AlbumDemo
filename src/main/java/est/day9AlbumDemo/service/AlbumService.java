package est.day9AlbumDemo.service;


import est.day9AlbumDemo.domain.Album;
import est.day9AlbumDemo.domain.Photo;
import est.day9AlbumDemo.domain.User;
import est.day9AlbumDemo.dto.AddAlbumRequest;
import est.day9AlbumDemo.dto.AlbumResponse;
import est.day9AlbumDemo.dto.UpdateAlbumRequest;
import est.day9AlbumDemo.repository.AlbumRepository;
import est.day9AlbumDemo.repository.PhotoRepository;
import est.day9AlbumDemo.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final UserRepository userRepository;
    private final PhotoRepository photoRepository;

    /**
     * 전체 앨범 + 썸네일 조회
     */
    public List<AlbumResponse> findAllWithThumbnail() {
        return albumRepository.findAll().stream()
                .map(album -> new AlbumResponse(album, getAlbumThumbnail(album.getAlbumId())))
                .toList();
    }

    /**
     * 단일 앨범 조회
     */
    public Album findById(Long albumId) {
        return albumRepository.findById(albumId)
                .orElseThrow(() -> new IllegalArgumentException("해당 Album ID가 없습니다: " + albumId));
    }

    /**
     * 특정 사용자 앨범 목록 조회 (썸네일 포함)
     */
    public List<AlbumResponse> getAlbumsByUser(Long userId) {
        return albumRepository.findByUser_UserId(userId).stream()
                .map(album -> new AlbumResponse(album, getAlbumThumbnail(album.getAlbumId())))
                .toList();
    }

    /**
     * 사용자 조회 (앨범 소유자 확인용)
     */
    public User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 User ID가 없습니다: " + userId));
    }

    /**
     * 앨범 썸네일 조회 (첫 번째 사진 기준)
     */
    public Photo getAlbumThumbnail(Long albumId) {
        return photoRepository.findAll().stream()
                .filter(p -> p.getAlbum().getAlbumId().equals(albumId))
                .findFirst()
                .orElse(null);
    }

    /**
     * 앨범 생성
     */
    public Album save(AddAlbumRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당 User ID가 없습니다: " + request.getUserId()));
        return albumRepository.save(request.toEntity(user));
    }

    /**
     * 앨범 수정
     */
    @Transactional
    public Album update(Long albumId, UpdateAlbumRequest request) {
        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new IllegalArgumentException("해당 Album ID가 없습니다: " + albumId));

        album = Album.builder()
                .albumId(album.getAlbumId()) // 기존 PK 유지
                .title(request.getTitle())
                .user(album.getUser())
                .build();

        return albumRepository.save(album);
    }

    /**
     * 앨범 삭제
     */
    public void delete(Long albumId) {
        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new IllegalArgumentException("해당 Album ID가 없습니다: " + albumId));

        // 연관된 사진 먼저 제거
        photoRepository.deleteAll(album.getPhotos());

        // 앨범 삭제
        albumRepository.delete(album);
    }
}