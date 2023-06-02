package backend.main.controller;

import backend.main.business.interfaces.service.IRecommendationService;
import backend.main.model.entity.MediaMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.*;

@RestController
@RequiredArgsConstructor
public class RecommendationController {
private final IRecommendationService iRecommendationService;

    @GetMapping("/recommend")
    public ResponseEntity<List<MediaMetadata>> processRecommendation(@RequestHeader("Token") String userToken) {

        List<MediaMetadata> response = iRecommendationService.recommend(userToken);

        return ResponseEntity.ok(response);
    }
}
