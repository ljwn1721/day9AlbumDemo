package est.day9AlbumDemo.repository;

import est.day9AlbumDemo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // 이메일로 사용자 조회 (로그인, 회원가입 시 필요)
    Optional<User> findByEmail(String email);

    // 특정 도시(city)에 거주하는 사용자 목록 조회
    List<User> findByCity(String city);
}