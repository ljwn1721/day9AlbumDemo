package est.day9AlbumDemo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class UserAlbumResponse {
    private UserResponse user;
    private List<AlbumResponse> albums;
}