package backend.main.controller;

import backend.main.business.interfaces.service.IUserService;
import backend.main.model.dto.UserAddDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class UserController {

    private final IUserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserAddDto userDto) {
        try {
            String id = userService.createUser(userDto);
            return new ResponseEntity<>(id, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/like/{mediaId}")
    public ResponseEntity<Integer> likeMedia(@PathVariable String mediaId) {
        // todo save like for media and return number of likes of media
        int likesCount = 0;
        return ResponseEntity.ok(likesCount);
    }
}