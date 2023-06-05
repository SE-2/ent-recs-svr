//package backend.main.controller;
//
//import backend.main.business.interfaces.service.*;
//import backend.main.model.entity.*;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.mock.web.MockMultipartFile;
//
//import java.io.IOException;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNull;
//import static org.mockito.Mockito.*;
//
//class MovieControllerTest {
//
//    @Mock
//    private IMovieService movieService;
//
//    @Mock
//    private IUserVisitedItemService userVisitedItemService;
//
//    private MovieController movieController;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        movieController = new MovieController(movieService, userVisitedItemService);
//    }
//
//    @Test
//    void importData_ValidFile_ReturnsCreatedResponse() {
//        MockMultipartFile file = new MockMultipartFile("movie", "data.csv", "text/csv", "file content".getBytes());
//        when(movieService.importDataFromCSV(file)).thenReturn(5);
//
//        ResponseEntity<String> response = movieController.importData(file);
//
//        assertEquals(HttpStatus.CREATED, response.getStatusCode());
//        assertEquals("Data imported successfully. 5", response.getBody());
//        verify(movieService, times(1)).importDataFromCSV(file);
//    }
//
//    @Test
//    void importData_EmptyFile_ReturnsBadRequestResponse() {
//        MockMultipartFile file = new MockMultipartFile("movie", "empty.csv", "text/csv", new byte[0]);
//
//        ResponseEntity<String> response = movieController.importData(file);
//
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//        assertEquals("No file selected", response.getBody());
//        verifyNoMoreInteractions(movieService);
//    }
//
//    @Test
//    void getMovie_ExistingMovie_ReturnsMovieAndSavesVisitedItem() {
//        String token = "testToken";
//        String movieId = "123";
//        Movie movie = Movie.builder().id(movieId).seriesTitle("Movie Title").director("Movie Title").build();
//
//        when(movieService.findMovie(movieId)).thenReturn(Optional.of(movie));
//
//        ResponseEntity<Movie> response = movieController.getMovie(token, movieId);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(movie, response.getBody());
//        verify(userVisitedItemService, times(1)).saveItemVisited(movieId, token);
//    }
//
//    @Test
//    void getMovie_NonExistingMovie_ReturnsNotFoundResponse() {
//        String token = "testToken";
//        String movieId = "123";
//
//        when(movieService.findMovie(movieId)).thenReturn(Optional.empty());
//
//        ResponseEntity<Movie> response = movieController.getMovie(token, movieId);
//
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//        assertNull(response.getBody());
//        verifyNoMoreInteractions(userVisitedItemService);
//    }
//}
//
