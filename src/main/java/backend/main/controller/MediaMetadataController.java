package backend.main.controller;

import backend.main.business.interfaces.service.ISearchMediaService;
import backend.main.model.dto.SearchQuery;
import backend.main.model.entity.MediaMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
public class MediaMetadataController {
    private final ISearchMediaService searchMediaService;

    @PostMapping("/search")
    public ResponseEntity<List<MediaMetadata>> search(@RequestBody SearchQuery searchQuery) {
        List<MediaMetadata> searchResults = searchMediaService.getMediaMetadata(searchQuery);
        return ResponseEntity.ok(searchResults);
    }
}
