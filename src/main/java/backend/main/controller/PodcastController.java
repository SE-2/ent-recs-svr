package backend.main.controller;

import backend.main.business.interfaces.metadataConvertor.IMovieToMetadataConvertor;
import backend.main.business.interfaces.metadataConvertor.IPodcastToMetadataConvertor;
import backend.main.business.interfaces.service.*;
import backend.main.model.dto.MediaMetadataDetails;
import backend.main.model.entity.Podcast;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class PodcastController {
    private final IPodcastService podcastService;
    private final IUserVisitedItemService userVisitedItemService;
    private final IPodcastToMetadataConvertor metadataConvertor;
    private final ILikeService likeService;
    private final IPlaylistItemService playlistItemService;
    private final IUserService userService;

    @PostMapping("/podcasts")
    public ResponseEntity<String> importData(@RequestParam("podcast") MultipartFile file) {
        if (file.isEmpty())
            return ResponseEntity.badRequest().body("No file selected");
        int savedCount = podcastService.importDataFromCSV(file);
        return ResponseEntity.status(HttpStatus.CREATED).body("Data imported successfully. " + savedCount);
    }

    @GetMapping("/podcasts/{podcastId}")
    public ResponseEntity<MediaMetadataDetails> getPodcast(@RequestHeader("Token")String token, @PathVariable String podcastId) {
        Optional<Podcast> optionalPodcast = podcastService.findPodcast(podcastId);
        if (optionalPodcast.isPresent()) {
            Podcast podcast = optionalPodcast.get();
            userVisitedItemService.saveItemVisited(podcast.getId(), token);
            var metadata = metadataConvertor.convertToMediaMetadata(List.of(podcast)).get(0);

            var response = MediaMetadataDetails.builder()
                    .metadata(metadata)
                    .decoration(podcast.getDescription())
                    .isBookMarked(playlistItemService.isBookmarked(podcastId, userService.getUser(token).getId()))
                    .isLiked(likeService.isLiked(metadata.getMediaId(), token))
                    .build();

            return ResponseEntity.ok(response);        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
