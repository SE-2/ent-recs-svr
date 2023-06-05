package backend.main.controller;

import backend.main.business.interfaces.metadataConvertor.IMovieToMetadataConvertor;
import backend.main.business.interfaces.service.*;
import backend.main.model.dto.MediaMetadataDetails;
import backend.main.model.entity.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class MovieController {
    private final IMovieService movieService;
    private final IUserVisitedItemService userVisitedItemService;
    private final IMovieToMetadataConvertor metadataConvertor;
    private final ILikeService likeService;
    private final IPlaylistItemService playlistItemService;
    private final IUserService userService;

    @PostMapping("/movies")
    public ResponseEntity<String> importData(@RequestParam("movie") MultipartFile file) {
        if (file.isEmpty())
            return ResponseEntity.badRequest().body("No file selected");
        int savedCount = movieService.importDataFromCSV(file);
        return ResponseEntity.status(HttpStatus.CREATED).body("Data imported successfully. " + savedCount);
    }

    @GetMapping("/movies/{movieId}")
    public ResponseEntity<MediaMetadataDetails> getMovie(@RequestHeader("Token")String token, @PathVariable String movieId) {
        Optional<Movie> optionalMovie = movieService.findMovie(movieId);
        if (optionalMovie.isPresent()) {
            Movie movie = optionalMovie.get();
            userVisitedItemService.saveItemVisited(movie.getId(), token);

            var metadata = metadataConvertor.convertToMediaMetadata(List.of(movie)).get(0);

            var response = MediaMetadataDetails.builder()
                    .metadata(metadata)
                    .decoration(movie.getOverview())
                    .isBookMarked(playlistItemService.isBookmarked(movieId, userService.getUser(token).getId()))
                    .isLiked(likeService.isLiked(metadata.getMediaId(), token))
                    .build();

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
