package est.day9AlbumDemo.controller;

import est.day9AlbumDemo.domain.Photo;
import est.day9AlbumDemo.dto.PhotoResponse;
import est.day9AlbumDemo.service.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/photos")
@RequiredArgsConstructor
public class PhotoController {

    private final PhotoService photoService;

    /**
     * 전체 사진 목록 조회
     * GET /api/photos
     */
    @GetMapping
    public ResponseEntity<List<PhotoResponse>> getAllPhotos() {
        List<PhotoResponse> responses = photoService.findAll()
                .stream()
                .map(PhotoResponse::new)
                .toList();

        return ResponseEntity.ok(responses);
    }

    /**
     * 특정 사진 단건 조회
     * GET /api/photos/{photoId}
     */
    @GetMapping("/{photoId}")
    public ResponseEntity<PhotoResponse> getPhotoById(@PathVariable Long photoId) {
        Photo photo = photoService.findById(photoId);
        return ResponseEntity.ok(new PhotoResponse(photo));
    }

    /**
     * 특정 앨범의 모든 사진 조회
     * GET /api/albums/{albumId}/photos
     */
    @GetMapping("/album/{albumId}")
    public ResponseEntity<List<PhotoResponse>> getPhotosByAlbum(@PathVariable Long albumId) {
        List<PhotoResponse> responses = photoService.findByAlbumId(albumId)
                .stream()
                .map(PhotoResponse::new)
                .toList();

        return ResponseEntity.ok(responses);
    }
}