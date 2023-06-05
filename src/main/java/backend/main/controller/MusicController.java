package backend.main.controller;

import backend.main.business.interfaces.metadataConvertor.IMovieToMetadataConvertor;
import backend.main.business.interfaces.metadataConvertor.IMusicToMetadataConvertor;
import backend.main.business.interfaces.service.*;
import backend.main.model.dto.MediaMetadataDetails;
import backend.main.model.entity.Music;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class MusicController {
    private final IMusicService musicService;
    private final IUserVisitedItemService userVisitedItemService;
    private final IMusicToMetadataConvertor metadataConvertor;
    private final ILikeService likeService;
    private final IPlaylistItemService playlistItemService;
    private final IUserService userService;

    @PostMapping("/musics")
    public ResponseEntity<String> importData(@RequestParam("music") MultipartFile file) {
        if (file.isEmpty())
            return ResponseEntity.badRequest().body("No file selected");
        int savedCount = musicService.importDataFromCSV(file);
        return ResponseEntity.status(HttpStatus.CREATED).body("Data imported successfully. " + savedCount);
    }

    @GetMapping("/musics/{musicId}")
    public ResponseEntity<MediaMetadataDetails> getMusic(@RequestHeader("Token") String token, @PathVariable String musicId) {
        Optional<Music> optionalMusic = musicService.findMusic(musicId);
        if (optionalMusic.isPresent()) {
            Music music = optionalMusic.get();
            userVisitedItemService.saveItemVisited(music.getId(), token);
            var metadata = metadataConvertor.convertToMediaMetadata(List.of(music)).get(0);

            var response = MediaMetadataDetails.builder()
                    .metadata(metadata)
                    .decoration(music.getDescription())
                    .isBookMarked(playlistItemService.isBookmarked(musicId, userService.getUser(token).getId()))
                    .isLiked(likeService.isLiked(metadata.getMediaId(), token))
                    .build();

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
