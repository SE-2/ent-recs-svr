package backend.main.business.interfaces.service;

import backend.main.model.entity.Movie;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface IMovieService {

    int importDataFromCSV(MultipartFile file);

    Optional<Movie> findMovie(String id);
}
