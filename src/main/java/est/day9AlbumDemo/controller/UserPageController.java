package est.day9AlbumDemo.controller;

import est.day9AlbumDemo.domain.User;
import est.day9AlbumDemo.dto.AddUserRequest;
import est.day9AlbumDemo.dto.AlbumResponse;
import est.day9AlbumDemo.dto.UserCityResponse;
import est.day9AlbumDemo.dto.UserResponse;
import est.day9AlbumDemo.service.AlbumService;
import est.day9AlbumDemo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserPageController {

    private final UserService userService;
    private final AlbumService albumService;

    /**
     * 회원가입 페이지 열기
     * GET /signup
     */
    @GetMapping("/signup")
    public String signupPage(Model model) {
        model.addAttribute("user", new AddUserRequest());

        return "signup"; // → templates/signup.html
    }

    /**
     * 회원가입 처리
     * POST /user
     */
    @PostMapping("/user")
    public String signup(@ModelAttribute AddUserRequest request) {
        userService.save(request);
        return "redirect:/login";
    }

    /**
     * 로그인 페이지 열기
     * GET /login
     */
    @GetMapping("/login")
    public String loginForm() {
        return "login"; // → templates/login.html
    }


    /**
     * 특정 사용자의 앨범 목록 페이지
     * GET /users/city
     */
    @GetMapping("/albums/user/{userId}")
    public String showUserAlbums(@PathVariable Long userId, Model model) {
        User user = userService.findById(userId);

        List<AlbumResponse> albums = albumService.getAlbumsByUser(userId);

        model.addAttribute("user", new UserResponse(user));
        model.addAttribute("albums", albums);

        return "user_albums"; // → templates/user_albums.html
    }


    /**
     * 특정 도시 사용자 목록 페이지
     * GET /users/city
     */
    @GetMapping("/users/city")
    public String showUsersByCity(@RequestParam String city, Model model) {
        List<UserCityResponse> users = userService.findByCity(city);
        model.addAttribute("city", city);
        model.addAttribute("users", users);

        return "users_by_city"; // → templates/users_by_city.html
    }

}