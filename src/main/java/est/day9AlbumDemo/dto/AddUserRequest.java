package est.day9AlbumDemo.dto;

import est.day9AlbumDemo.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 사용자 생성 요청 DTO
 * 클라이언트 → 서버 (Request)
 */
@Getter
@Setter
@NoArgsConstructor
public class AddUserRequest {
    private String name;
    private String email;
    private String company;
    private String phone;
    private String city;
    private String password;

    public User toEntity(String encodedPassword) {
        return User.builder()
                .name(name)
                .email(email)
                .company(company)
                .phone(phone)
                .city(city)
                .password(encodedPassword)
                .build();
    }
}