package est.day9AlbumDemo.dto;

import est.day9AlbumDemo.domain.Album;
import est.day9AlbumDemo.domain.Photo;
import lombok.Getter;

@Getter
public class AlbumResponse {
    private Long albumId;
    private String title;
    private Long userId;
    private String thumbnailUrl;

    public AlbumResponse(Album album, Photo photo) {
        this.albumId = album.getAlbumId();
        this.title = album.getTitle();
        this.userId = album.getUser().getUserId();
        this.thumbnailUrl = (photo != null ? photo.getThumbnail() : null);
    }
}