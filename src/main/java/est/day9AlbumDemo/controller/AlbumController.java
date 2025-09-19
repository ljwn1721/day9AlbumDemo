package est.day9AlbumDemo.controller;

import est.day9AlbumDemo.domain.Album;
import est.day9AlbumDemo.dto.*;
import est.day9AlbumDemo.service.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/albums")
@RequiredArgsConstructor
public class AlbumController {

    private final AlbumService albumService;

    /**
     * 전체 앨범 목록 조회
     * GET /api/albums
     */
    @GetMapping
    public ResponseEntity<List<AlbumResponse>> getAllAlbums() {
        List<AlbumResponse> responses = albumService.findAllWithThumbnail();
        return ResponseEntity.ok(responses);
    }

    /**
     * 특정 앨범 단건 조회
     * GET /api/albums/{albumId}
     */
    @GetMapping("/{albumId}")
    public ResponseEntity<AlbumResponse> getAlbumById(@PathVariable Long albumId) {
        Album album = albumService.findById(albumId);
        return ResponseEntity.ok(new AlbumResponse(album, albumService.getAlbumThumbnail(albumId)));
    }

    /**
     * 특정 사용자(userId)의 앨범 목록 조회
     * GET /api/albums/user/{userId}
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<UserAlbumResponse> getAlbumsByUser(@PathVariable Long userId) {
        List<AlbumResponse> albums = albumService.getAlbumsByUser(userId);
        UserResponse user = new UserResponse(albumService.findUserById(userId));
        return ResponseEntity.ok(new UserAlbumResponse(user, albums));
    }

    /**
     * 앨범 생성
     * POST /api/albums
     */
    @PostMapping
    public ResponseEntity<AlbumResponse> addAlbum(@RequestBody AddAlbumRequest request) {
        Album savedAlbum = albumService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new AlbumResponse(savedAlbum, albumService.getAlbumThumbnail(savedAlbum.getAlbumId())));
    }

    /**
     * 앨범 수정
     * PUT /api/albums/{albumId}
     */
    @PutMapping("/{albumId}")
    public ResponseEntity<AlbumResponse> updateAlbum(@PathVariable Long albumId,
                                                     @RequestBody UpdateAlbumRequest request) {
        Album updated = albumService.update(albumId, request);
        return ResponseEntity.ok(new AlbumResponse(updated, albumService.getAlbumThumbnail(albumId)));
    }

    /**
     * 앨범 삭제
     * DELETE /api/albums/{albumId}
     */
    @DeleteMapping("/{albumId}")
    public ResponseEntity<Void> deleteAlbum(@PathVariable Long albumId) {
        albumService.delete(albumId);
        return ResponseEntity.noContent().build();
    }
}