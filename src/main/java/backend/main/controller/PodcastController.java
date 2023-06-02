package backend.main.controller;

import backend.main.business.interfaces.service.IPodcastService;
import backend.main.model.entity.Movie;
import backend.main.model.entity.Podcast;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class PodcastController {
    private final IPodcastService podcastService;

    @PostMapping("/podcasts")
    public ResponseEntity<String> importData(@RequestParam("podcast") MultipartFile file) {
        if (file.isEmpty())
            return ResponseEntity.badRequest().body("No file selected");
        podcastService.importDataFromCSV(file);
        return ResponseEntity.status(HttpStatus.CREATED).body("Data imported successfully");
    }

    @GetMapping("/podcast/{podcastId}")
    public ResponseEntity<Podcast> getPodcast(@PathVariable String podcastId) {
        return ResponseEntity.ok(new Podcast());
    }
}
