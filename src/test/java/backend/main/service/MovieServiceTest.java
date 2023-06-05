package backend.main.service;
import backend.main.business.implementation.service.MovieService;
import backend.main.business.interfaces.deserializer.IMovieDeserializer;
import backend.main.business.interfaces.parser.IFileParser;
import backend.main.model.entity.Movie;
import backend.main.repository.MovieRepository;
import com.opencsv.exceptions.CsvValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MovieServiceTest {
    @Mock
    private IFileParser fileParser;

    @Mock
    private IMovieDeserializer movieDeserializer;

    @Mock
    private MovieRepository movieRepository;

    private MovieService movieService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        movieService = new MovieService(fileParser, movieDeserializer, movieRepository);
    }

    @Test
    public void importDataFromCSV_ValidFile_ReturnsSavedCount() throws IOException, CsvValidationException {
        MultipartFile mockFile = new MockMultipartFile("data.csv", "content".getBytes());
        List<String[]> mockLines = Arrays.asList(new String[]{"1", "Movie 1"}, new String[]{"2", "Movie 2"});
        List<Movie> mockMovies = Arrays.asList(Movie.builder().id("1").seriesTitle("movie 1").build(),Movie.builder().id("2").seriesTitle("movie 2").build());

        when(fileParser.parse(mockFile)).thenReturn(mockLines);
        when(movieDeserializer.deserialize(mockLines)).thenReturn(mockMovies);
        when(movieRepository.saveAll(mockMovies)).thenReturn(mockMovies);

        int savedCount = movieService.importDataFromCSV(mockFile);

        assertEquals(mockMovies.size(), savedCount);
        verify(fileParser).parse(mockFile);
        verify(movieDeserializer).deserialize(mockLines);
        verify(movieRepository).saveAll(mockMovies);
    }

    @Test
    public void findMovie_ExistingId_ReturnsMovie() {
        String mockMovieId = "1";
        Movie mockMovie = Movie.builder().id(mockMovieId).seriesTitle("movie 1").build();

        when(movieRepository.findById(mockMovieId)).thenReturn(Optional.of(mockMovie));

        Optional<Movie> foundMovie = movieService.findMovie(mockMovieId);

        assertEquals(Optional.of(mockMovie), foundMovie);
        verify(movieRepository).findById(mockMovieId);
    }

    @Test
    public void findMovie_NonExistingId_ReturnsEmptyOptional() {
        String mockMovieId = "1";

        when(movieRepository.findById(mockMovieId)).thenReturn(Optional.empty());

        Optional<Movie> foundMovie = movieService.findMovie(mockMovieId);

        assertEquals(Optional.empty(), foundMovie);
        verify(movieRepository).findById(mockMovieId);
    }
}

