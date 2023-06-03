package backend.main.controller;

import backend.main.business.interfaces.service.IPodcastService;
import backend.main.business.interfaces.service.IUserVisitedItemService;
import backend.main.model.entity.Podcast;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class PodcastController {
    private final IPodcastService podcastService;
    private final IUserVisitedItemService userVisitedItemService;

    @PostMapping("/podcasts")
    public ResponseEntity<String> importData(@RequestParam("podcast") MultipartFile file) {
        if (file.isEmpty())
            return ResponseEntity.badRequest().body("No file selected");
        int savedCount = podcastService.importDataFromCSV(file);
        return ResponseEntity.status(HttpStatus.CREATED).body("Data imported successfully. " + savedCount);
    }

    @GetMapping("/podcast/{podcastId}")
    public ResponseEntity<Podcast> getPodcast(@RequestHeader("Token")String token, @PathVariable String podcastId) {
        Optional<Podcast> optionalPodcast = podcastService.findPodcast(podcastId);
        if (optionalPodcast.isPresent()) {
            Podcast podcast = optionalPodcast.get();
            userVisitedItemService.saveItemVisited(podcast.getId(), token);
            return ResponseEntity.ok(podcast);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
