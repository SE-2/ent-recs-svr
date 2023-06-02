package backend.main.controller;

import backend.main.business.interfaces.service.IUserService;
import backend.main.model.dto.UserAddDto;
import backend.main.model.entity.User;
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
        User user = User.builder()
                .email(userDto.getEmail())
                .birthDate(userDto.getBirthDate())
                .token(userDto.getToken())
                .build();

        try {
            userService.createUser(user);
            return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}