package backend.main.controller;


import backend.main.business.interfaces.service.ITrendMediaService;
import backend.main.model.dto.MediaType;
import backend.main.model.entity.MediaMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequiredArgsConstructor
public class TrendMediaController {

    private final ITrendMediaService itrendMediaService;

    @GetMapping("/trends/today")
    public ResponseEntity<List<MediaMetadata>> getTodayTrendMedia() {
        List<MediaMetadata> trends = itrendMediaService.getTodayTrendMedia();
        return ResponseEntity.ok(trends);
    }

    @GetMapping("/trends/all")
    public ResponseEntity<List<MediaMetadata>> getAllTimesTrendMedia() {
        List<MediaMetadata> trends = itrendMediaService.getAllTimeTrendMedia();
        return ResponseEntity.ok(trends);
    }

}
