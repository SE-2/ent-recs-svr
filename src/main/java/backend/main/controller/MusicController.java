package backend.main.controller;

import backend.main.business.interfaces.service.IMusicService;
import backend.main.model.entity.Movie;
import backend.main.model.entity.Music;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class MusicController {
    private final IMusicService musicService;

    @PostMapping("/musics")
    public ResponseEntity<String> importData(@RequestParam("music") MultipartFile file) {
        if (file.isEmpty())
            return ResponseEntity.badRequest().body("No file selected");
        musicService.importDataFromCSV(file);
        return ResponseEntity.status(HttpStatus.CREATED).body("Data imported successfully");
    }

    @GetMapping("/music/{musicId}")
    public ResponseEntity<Music> getMusic(@PathVariable String musicId) {
        return ResponseEntity.ok(new Music());
    }
}
