package backend.main.controller;

import backend.main.business.interfaces.service.IPlaylistsService;
import backend.main.business.interfaces.service.IUserService;
import backend.main.model.dto.CreatePlaylistDto;
import backend.main.model.entity.MediaMetadata;
import backend.main.model.entity.Playlists;
import backend.main.model.entity.User;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PlaylistController {
    private final IPlaylistsService playlistsService;
    private final IUserService userService;

    @PostMapping("/playlist/create")
    public ResponseEntity<String> createPlaylist(@RequestHeader("Token") String token, @RequestBody CreatePlaylistDto createPlaylistDto) {
        User user = userService.getUser(token);
        playlistsService.createPlaylist(user, createPlaylistDto.getName(), createPlaylistDto.getMediaTypes());
        return ResponseEntity.ok().body("playlist created successfully! ");

    }

    @PostMapping("/playlist/delete/{playlistID}")
    public ResponseEntity<String> deletePlaylist(@RequestHeader("Token") String token, @PathVariable Integer playlistID) {
        User user = userService.getUser(token);
        playlistsService.deletePlaylist(playlistID, user);
        return ResponseEntity.ok().body("playlist deleted successfully! ");

    }

    @GetMapping("/playlist/show/all")
    public ResponseEntity<List<Playlists>> showAllPlaylists(@RequestHeader("Token") String token) {
        User user = userService.getUser(token);
        List<Playlists> playlists = playlistsService.getAllPlaylistsByUserId(user.getId());
        return ResponseEntity.ok().body(playlists);
    }

    @GetMapping("/playlist/items/{playlistID}")
    public ResponseEntity<List<MediaMetadata>> showPlaylistItems(@RequestHeader("Token") String token, @PathVariable String playlistID) {
        User user = userService.getUser(token);
        List<MediaMetadata> items = playlistsService.getPlaylistItems(playlistID);
        return ResponseEntity.ok().body(items);
    }

    @PostMapping("/playlist/edit/{playlistID}")
    public ResponseEntity<String> editPlaylist(@RequestHeader("Token") String token, @PathVariable String playlistID, @RequestBody CreatePlaylistDto createPlaylistDto) {
        User user = userService.getUser(token);
        playlistsService.editPlaylist(user, createPlaylistDto.getName(), createPlaylistDto.getMediaTypes(), playlistID);
        return ResponseEntity.ok().body("playlist edited successfully! ");

    }
}
