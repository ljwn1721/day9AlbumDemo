package est.day9AlbumDemo.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "photos")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB PK 자동 증가
    private Long photoId;

    @Column(name = "external_id", unique = true) // 외부 API id 저장
    private Long externalId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id") // FK → Album.albumId
    private Album album;

    private String thumbnail; // 썸네일 URL

    @Builder
    public Photo(Long photoId, Long externalId, Album album, String thumbnail) {
        this.photoId = photoId;
        this.externalId = externalId;
        this.album = album;
        this.thumbnail = thumbnail;
    }
}
