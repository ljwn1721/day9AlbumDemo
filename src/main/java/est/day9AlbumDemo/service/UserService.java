package est.day9AlbumDemo.service;


import est.day9AlbumDemo.domain.User;
import est.day9AlbumDemo.dto.AddUserRequest;
import est.day9AlbumDemo.dto.UserCityResponse;
import est.day9AlbumDemo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // 비밀번호 암호화

    /**
     * 사용자 저장 (회원가입)
     */
    public User save(AddUserRequest request) {
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .company(request.getCompany())
                .phone(request.getPhone())
                .city(request.getCity())
                // 비밀번호는 반드시 암호화해서 저장
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        return userRepository.save(user);
    }

    /**
     * 특정 사용자 조회
     */
    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자 ID가 없습니다: " + userId));
    }

    /**
     * 특정 도시의 사용자 조회
     */
    public List<UserCityResponse> findByCity(String city) {
        return userRepository.findByCity(city).stream()
                .map(UserCityResponse::new) // User → UserCityResponse DTO 변환
                .toList();
    }

    /**
     * 이메일로 사용자 조회 (일반 조회)
     */
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("해당 이메일이 존재하지 않습니다: " + email));
    }

    /**
     * 로그인 시 호출되는 메서드
     * Spring Security가 자동으로 실행
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("해당 이메일이 존재하지 않습니다: " + email));
    }
}