package est.day9AlbumDemo.dto;

import est.day9AlbumDemo.domain.Album;
import est.day9AlbumDemo.domain.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddAlbumRequest {
    private String title;
    private Long userId; // 로그인된 사용자 ID

    public Album toEntity(User user) {
        return Album.builder()
                .title(title)
                .user(user)
                .build();
    }
}
