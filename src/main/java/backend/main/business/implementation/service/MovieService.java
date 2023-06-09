package backend.main.business.implementation.service;

import backend.main.business.interfaces.parser.IFileParser;
import backend.main.business.interfaces.deserializer.IMovieDeserializer;
import backend.main.business.interfaces.service.IMovieService;
import backend.main.model.entity.Movie;
import backend.main.repository.MovieRepository;
import com.opencsv.exceptions.CsvValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

@RequiredArgsConstructor
@Service
public class MovieService implements IMovieService {
    private final IFileParser fileParser;
    private final IMovieDeserializer movieDeserializer;
    private final MovieRepository movieRepository;

    public int importDataFromCSV(MultipartFile file) {
        int savedCount = 0;
        try {
            List<String[]> lines = fileParser.parse(file);
            List<Movie> movies = movieDeserializer.deserialize(lines);
            movieRepository.saveAll(movies);
            savedCount = movies.size();
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        return savedCount;
    }

    @Override
    public Optional<Movie> findMovie(String id) {
        return movieRepository.findById(id);
    }
}
