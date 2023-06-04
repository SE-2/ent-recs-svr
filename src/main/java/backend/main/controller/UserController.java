package backend.main.controller;

import backend.main.business.interfaces.service.IUserService;
import backend.main.model.dto.*;
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
        try {
            String id = userService.createUser(userDto);
            return new ResponseEntity<>(id, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getUser")
    public ResponseEntity<UserGetDto> getUser(@RequestHeader("Token") String token) {
        UserGetDto user = userService.getUser(token);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}