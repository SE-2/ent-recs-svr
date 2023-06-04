package backend.main.controller;

import backend.main.business.interfaces.service.IPlaylistItemService;
import backend.main.business.interfaces.service.IUserService;
import backend.main.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PlaylistItemController {
    private final IPlaylistItemService playlistItemService;
    private final IUserService iUserService;

    @PostMapping("/addItemToPlaylist/{playlistId}/{mediaId}")
    public ResponseEntity<String> addItemToPlaylist(@RequestHeader("Token") String token, @PathVariable String playlistId, @PathVariable String mediaId) {
        User user = iUserService.getUser(token);
        playlistItemService.addItemToPlaylist(user.getId(), playlistId, mediaId);
        return ResponseEntity.ok().body("item added to playlist successfully! ");
    }

    @PostMapping("/deleteItemFromPlaylist/{mediaId}")
    public ResponseEntity<String> deleteItemFromPlaylist(@RequestHeader("Token") String token, @PathVariable String mediaId) {
        User user = iUserService.getUser(token);
        playlistItemService.deleteItemFromPlaylist(user.getId(), mediaId);
        return ResponseEntity.ok().body("item deleted to playlist successfully! ");
    }
}
