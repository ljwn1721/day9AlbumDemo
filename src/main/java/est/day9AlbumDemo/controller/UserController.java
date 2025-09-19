package est.day9AlbumDemo.controller;

import est.day9AlbumDemo.domain.User;
import est.day9AlbumDemo.dto.AddUserRequest;
import est.day9AlbumDemo.dto.UserCityResponse;
import est.day9AlbumDemo.dto.UserResponse;
import est.day9AlbumDemo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 특정 사용자 조회
     * GET /api/users/{userId}
     */
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long userId) {
        User user = userService.findById(userId);
        return ResponseEntity.ok(new UserResponse(user));
    }

    /**
     * 특정 도시 사용자 목록 조회
     * GET /api/users/city/{city}
     */
    @GetMapping("/city/{city}")
    public ResponseEntity<List<UserCityResponse>> getUsersByCity(@PathVariable String city) {
        List<UserCityResponse> users = userService.findByCity(city);
        return ResponseEntity.ok(users);
    }

    /**
     * 사용자 등록 (회원가입)
     * POST /api/users
     */
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody AddUserRequest request) {
        User savedUser = userService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new UserResponse(savedUser));
    }

}