package est.day9AlbumDemo.dto;

import est.day9AlbumDemo.domain.Photo;
import lombok.Getter;

@Getter
public class PhotoResponse {
    private Long photoId;
    private Long albumId;
    private String thumbnail;

    public PhotoResponse(Photo photo) {
        this.photoId = photo.getPhotoId();
        this.albumId = photo.getAlbum().getAlbumId();
        this.thumbnail = photo.getThumbnail();
    }
}
