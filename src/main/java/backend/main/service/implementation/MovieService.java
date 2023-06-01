package backend.main.service.implementation;

import backend.main.Interfaces.IFileParser;
import backend.main.Interfaces.IMovieDeserializer;
import backend.main.model.entity.Movie;
import backend.main.repository.MovieRepository;
import backend.main.service.interfaces.IMovieService;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

@RequiredArgsConstructor
@Service
public class MovieService {
    private final IFileParser fileParser;
    private final IMovieDeserializer movieDeserializer;
    private final MovieRepository movieRepository;

    public void importDataFromCSV(MultipartFile file) {
        try {
            List<String[]> lines = fileParser.parse(file);
            List<Movie> movies = movieDeserializer.deserialize(lines);
            movieRepository.saveAll(movies);
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }
}
