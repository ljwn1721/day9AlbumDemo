package est.day9AlbumDemo.dto;

import est.day9AlbumDemo.domain.User;
import lombok.Getter;

/**
 * 특정 도시 사용자 목록 조회용 DTO
 * 서버 → 클라이언트 (Response)
 */
@Getter
public class UserCityResponse {
    private final String name;
    private final String email;
    private final String company;

    public UserCityResponse(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.company = user.getCompany();
    }
}
