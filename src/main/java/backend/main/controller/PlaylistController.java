package backend.main.controller;

import backend.main.business.interfaces.service.IPlaylistsService;
import backend.main.business.interfaces.service.IUserService;
import backend.main.model.dto.CreatePlaylistDto;
import backend.main.model.entity.Music;
import backend.main.model.entity.User;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class PlaylistController {
    private final IPlaylistsService iPlaylistsService;
    private final IUserService iUserService;

    @GetMapping("/createPlaylist")
    public ResponseEntity<String> createPlaylist(@RequestHeader("Token") String token, @RequestBody CreatePlaylistDto createPlaylistDto) {
        User user = iUserService.getUser(token);
        iPlaylistsService.createPlaylist(user, createPlaylistDto.getName(), createPlaylistDto.getMediaTypes());
        return ResponseEntity.ok().body("playlist created successfully! ");

    }
    @GetMapping("/deletePlaylist/{playlistID}")
    public ResponseEntity<String> deletePlaylist(@RequestHeader("Token") String token, @PathVariable String playlistID) {
        User user = iUserService.getUser(token);
        iPlaylistsService.deletePlaylist(playlistID);
        return ResponseEntity.ok().body("playlist deleted successfully! ");

    }
}
