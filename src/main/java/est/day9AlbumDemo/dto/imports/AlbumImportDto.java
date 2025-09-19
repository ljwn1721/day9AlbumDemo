package est.day9AlbumDemo.dto.imports;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AlbumImportDto {
    private Long userId;  // User FK
    private Long id;      // Album PK
    private String title;
}
