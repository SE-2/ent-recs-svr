package backend.main.controller;

import backend.main.business.interfaces.service.IUserService;
import backend.main.business.interfaces.updater.IFavoriteBookUpdater;
import backend.main.model.dto.*;
import backend.main.model.entity.Music;
import backend.main.model.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
        User user = userService.getUser(token);
        UserGetDto user1 = new UserGetDto(user.getName(), user.getProfileImgUrl());
        return new ResponseEntity<>(user1, HttpStatus.CREATED);
    }

    @PostMapping("/interests")
    public ResponseEntity<String> updateInterests(@RequestHeader("Token") String token, @RequestBody UserFavoriteDto userFavoriteDto) {
        try {
            User user = userService.getUser(token);
            userService.updateInterests(user, userFavoriteDto);
            return ResponseEntity.ok().body("interests updated successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/interests/submitted/{mediaType}")
    public ResponseEntity<Boolean> updateInterests(@RequestHeader("Token") String token, @PathVariable MediaType mediaType) {
        boolean getFilled;
        User user = userService.getUser(token);
        getFilled = userService.getFilled(user, mediaType);
        return ResponseEntity.ok(getFilled);

    }
}