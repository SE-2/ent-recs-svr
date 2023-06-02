package backend.main.controller;


import backend.main.business.interfaces.service.ITrendMediaService;
import backend.main.model.entity.MediaMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TrendMediaController {

    private ITrendMediaService trendMediaService;

    @GetMapping("/trends/today")
    public ResponseEntity<List<MediaMetadata>> getTodayTrendMedia() {
        return ResponseEntity.ok(new ArrayList<>());
    }

    @GetMapping("/trends/all")
    public ResponseEntity<List<MediaMetadata>> getAllTimesTrendMedia() {
        return ResponseEntity.ok(new ArrayList<>());
    }

}
