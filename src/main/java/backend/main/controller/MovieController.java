package backend.main.controller;

import backend.main.business.interfaces.service.IMovieService;
import backend.main.business.interfaces.service.IUserVisitedItemService;
import backend.main.model.entity.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class MovieController {
    private final IMovieService movieService;
    private final IUserVisitedItemService userVisitedItemService;

    @PostMapping("/movies")
    public ResponseEntity<String> importData(@RequestParam("movie") MultipartFile file) {
        if (file.isEmpty())
            return ResponseEntity.badRequest().body("No file selected");
        int savedCount = movieService.importDataFromCSV(file);
        return ResponseEntity.status(HttpStatus.CREATED).body("Data imported successfully. " + savedCount);
    }

    @GetMapping("/movie/{movieId}")
    public ResponseEntity<Movie> getMovie(@RequestHeader("Token")String token, @PathVariable String movieId) {
        Optional<Movie> optionalMovie = movieService.findMovie(movieId);
        if (optionalMovie.isPresent()) {
            Movie movie = optionalMovie.get();
            userVisitedItemService.saveItemVisited(movie.getId(), token);
            return ResponseEntity.ok(movie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
