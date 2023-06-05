package backend.main.controller;


import backend.main.business.interfaces.service.IUserVisitedItemService;
import backend.main.model.entity.MediaMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class UserVisitedItemController {

    private final IUserVisitedItemService userVisitedItemService;

    @GetMapping("/recent")
    public ResponseEntity<List<MediaMetadata>> getLastVisitedItems(@RequestHeader("Token")String token) {
        List<MediaMetadata> lastVisited = userVisitedItemService.getRecentVisitedItems(token);
        return ResponseEntity.ok(lastVisited);
    }

}
