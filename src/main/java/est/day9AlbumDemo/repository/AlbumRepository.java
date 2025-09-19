package est.day9AlbumDemo.repository;

import est.day9AlbumDemo.domain.Album;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Long> {
    // 특정 사용자(userId)의 앨범 목록 조회
    List<Album> findByUser_UserId(Long userId);
}