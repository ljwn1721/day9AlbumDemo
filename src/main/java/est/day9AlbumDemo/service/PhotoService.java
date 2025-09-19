package est.day9AlbumDemo.service;

import est.day9AlbumDemo.domain.Photo;
import est.day9AlbumDemo.repository.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PhotoService {

    private final PhotoRepository photoRepository;

    /**
     * 전체 사진 목록 조회
     */
    public List<Photo> findAll() {
        return photoRepository.findAll();
    }

    /**
     * 특정 사진 단건 조회
     */
    public Photo findById(Long photoId) {
        return photoRepository.findById(photoId)
                .orElseThrow(() -> new IllegalArgumentException("해당 Photo ID가 없습니다: " + photoId));
    }

    /**
     * 특정 앨범에 속한 모든 사진 조회
     */
    public List<Photo> findByAlbumId(Long albumId) {
        return photoRepository.findByAlbum_AlbumId(albumId);
    }
}
