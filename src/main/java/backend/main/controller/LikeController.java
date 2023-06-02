package backend.main.controller;

import backend.main.business.interfaces.service.ILikeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class LikeController {
    private final ILikeService likeService;

    @PatchMapping("/like/{mediaId}")
    public ResponseEntity<Integer> likeMedia(@RequestHeader("Token")String token, @PathVariable String mediaId) {
        int likesCount = likeService.likeMedia(mediaId,token);
        return ResponseEntity.ok(likesCount);

    }
}
