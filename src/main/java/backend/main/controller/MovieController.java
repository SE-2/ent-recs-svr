package backend.main.controller;

import backend.main.business.interfaces.service.IMovieService;
import backend.main.model.entity.Book;
import backend.main.model.entity.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class MovieController {
    private final IMovieService movieService;

    @PostMapping("/movies")
    public ResponseEntity<String> importData(@RequestParam("movie") MultipartFile file) {
        if (file.isEmpty())
            return ResponseEntity.badRequest().body("No file selected");
        movieService.importDataFromCSV(file);
        return ResponseEntity.status(HttpStatus.CREATED).body("Data imported successfully");
    }

    @GetMapping("/movie/{movieId}")
    public ResponseEntity<Movie> getMovie(@PathVariable String movieId) {
        return ResponseEntity.ok(new Movie());
    }
}
