package est.day9AlbumDemo.dto.imports;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PhotoImportDto {
    private Long albumId;   // Album FK
    private Long id;        // Photo PK

    @JsonProperty("thumbnailUrl")
    private String thumbnail;  // Photo.thumbnail
}
