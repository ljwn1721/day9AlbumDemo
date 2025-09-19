package est.day9AlbumDemo.controller;

import est.day9AlbumDemo.domain.Album;
import est.day9AlbumDemo.domain.User;
import est.day9AlbumDemo.dto.AddAlbumRequest;
import est.day9AlbumDemo.dto.AlbumResponse;
import est.day9AlbumDemo.dto.PhotoResponse;
import est.day9AlbumDemo.dto.UpdateAlbumRequest;
import est.day9AlbumDemo.service.AlbumService;
import est.day9AlbumDemo.service.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AlbumPageController {

    private final AlbumService albumService;
    private final PhotoService photoService;

    /**
     * 앨범 목록 페이지
     * GET /albums
     */
    @GetMapping("/albums")
    public String showAlbums(Model model) {
        List<AlbumResponse> albums = albumService.findAllWithThumbnail();
        model.addAttribute("albums", albums);
        return "albums"; // → templates/albums.html
    }

    /**
     * 앨범 생성/수정 폼
     * GET /albums/new
     * GET /albums/{albumId}/edit
     */
    @GetMapping({"/albums/new", "/albums/{albumId}/edit"})
    public String albumForm(@PathVariable(required = false) Long albumId, Model model) {
        if (albumId != null) { // 수정 모드
            Album album = albumService.findById(albumId);
            UpdateAlbumRequest dto = new UpdateAlbumRequest();
            dto.setTitle(album.getTitle());

            model.addAttribute("album", dto);
            model.addAttribute("albumId", albumId);
            model.addAttribute("mode", "edit");
        } else { // 생성 모드
            model.addAttribute("album", new AddAlbumRequest());
            model.addAttribute("mode", "new");
        }
        return "album_form"; // → templates/album_form.html
    }

    /**
     * 앨범 생성 처리
     * POST /albums
     */
    @PostMapping("/albums")
    public String createAlbum(@ModelAttribute AddAlbumRequest request,
                              @AuthenticationPrincipal User user) {
        request.setUserId(user.getUserId()); // 로그인한 사용자 ID 설정
        albumService.save(request);
        return "redirect:/albums";
    }

    /**
     * 앨범 수정 처리
     * POST /albums/{albumId}/edit
     */
    @PostMapping("/albums/{albumId}/edit")
    public String updateAlbum(@PathVariable Long albumId,
                              @ModelAttribute UpdateAlbumRequest request) {
        albumService.update(albumId, request);
        return "redirect:/albums";
    }

    /**
     * 앨범 삭제 처리
     * POST /albums/{albumId}/delete
     */
    @PostMapping("/albums/{albumId}/delete")
    public String deleteAlbum(@PathVariable Long albumId) {
        albumService.delete(albumId);
        return "redirect:/albums";
    }

    /**
     * 특정 앨범의 사진 페이지
     * GET /albums/{albumId}
     */
    @GetMapping("/albums/{albumId}")
    public String showAlbumPhotos(@PathVariable Long albumId, Model model) {
        List<PhotoResponse> photos = photoService.findByAlbumId(albumId)
                .stream()
                .map(PhotoResponse::new)
                .toList();

        model.addAttribute("albumId", albumId);
        model.addAttribute("photos", photos);
        return "album_photos"; // → templates/album_photos.html
    }
}