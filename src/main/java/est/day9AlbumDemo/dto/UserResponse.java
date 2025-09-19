package est.day9AlbumDemo.dto;

import lombok.Getter;
import est.day9AlbumDemo.domain.User;

@Getter
public class UserResponse {
    private final String name;
    private final String email;
    private final String company;

    public UserResponse(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.company = user.getCompany();
    }
}