package est.day9AlbumDemo.repository;

import est.day9AlbumDemo.domain.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
    // 특정 앨범(albumId)에 속한 사진 목록 조회
    List<Photo> findByAlbum_AlbumId(Long albumId);
}