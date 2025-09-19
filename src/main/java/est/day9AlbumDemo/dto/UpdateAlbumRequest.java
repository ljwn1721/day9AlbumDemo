package est.day9AlbumDemo.dto;

import est.day9AlbumDemo.domain.Album;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateAlbumRequest {
    private String title;

    public void updateEntity(Album album) {
        album = Album.builder()
                .albumId(album.getAlbumId())
                .title(this.title)
                .user(album.getUser())
                .build();
    }
}