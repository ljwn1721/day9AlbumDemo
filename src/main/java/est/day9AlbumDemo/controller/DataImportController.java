package est.day9AlbumDemo.controller;

import est.day9AlbumDemo.service.DataImportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/import")
@RequiredArgsConstructor
public class DataImportController {

    private final DataImportService dataImportService;

    /**
     * User 데이터 import
     * POST /api/import/users
     */
    @PostMapping("/users")
    public ResponseEntity<String> importUsers() {
        dataImportService.importUsers();
        return ResponseEntity.ok("User 데이터 저장 완료");
    }

    /**
     * Album 데이터 import
     * POST /api/import/albums
     */
    @PostMapping("/albums")
    public ResponseEntity<String> importAlbums() {
        dataImportService.importAlbums();
        return ResponseEntity.ok("Album 데이터 저장 완료");
    }

    /**
     * Photo 데이터 import
     * POST /api/import/photos
     */
    @PostMapping("/photos")
    public ResponseEntity<String> importPhotos() {
        dataImportService.importPhotos();
        return ResponseEntity.ok("Photo 데이터 저장 완료");
    }

    /**
     * 전체 데이터 import (User → Album → Photo 순서)
     * POST /api/import/all
     */
    @PostMapping("/all")
    public ResponseEntity<String> importAll() {
        dataImportService.importUsers();
        dataImportService.importAlbums();
        dataImportService.importPhotos();
        return ResponseEntity.ok("모든 데이터 저장 완료 (Users → Albums → Photos)");
    }
}