//package backend.main.controller;
//
//import backend.main.business.interfaces.service.*;
//import backend.main.model.entity.Music;
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
//class MusicControllerTest {
//
//    @Mock
//    private IMusicService musicService;
//
//    @Mock
//    private IUserVisitedItemService userVisitedItemService;
//
//    private MusicController musicController;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        musicController = new MusicController(musicService, userVisitedItemService);
//    }
//
//    @Test
//    void importData_ValidFile_ReturnsCreatedResponse() {
//        MockMultipartFile file = new MockMultipartFile("music", "data.csv", "text/csv", "file content".getBytes());
//        when(musicService.importDataFromCSV(file)).thenReturn(5);
//
//        ResponseEntity<String> response = musicController.importData(file);
//
//        assertEquals(HttpStatus.CREATED, response.getStatusCode());
//        assertEquals("Data imported successfully. 5", response.getBody());
//        verify(musicService, times(1)).importDataFromCSV(file);
//    }
//
//    @Test
//    void importData_EmptyFile_ReturnsBadRequestResponse() {
//        MockMultipartFile file = new MockMultipartFile("music", "empty.csv", "text/csv", new byte[0]);
//
//        ResponseEntity<String> response = musicController.importData(file);
//
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//        assertEquals("No file selected", response.getBody());
//        verifyNoMoreInteractions(musicService);
//    }
//
//    @Test
//    void getMusic_ExistingMusic_ReturnsMusicAndSavesVisitedItem() {
//        String token = "testToken";
//        String musicId = "123";
//        Music music = Music.builder().id(musicId).title("Music Title").artist("Artist").build();
//
//        when(musicService.findMusic(musicId)).thenReturn(Optional.of(music));
//
//        ResponseEntity<Music> response = musicController.getMusic(token, musicId);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(music, response.getBody());
//        verify(userVisitedItemService, times(1)).saveItemVisited(musicId, token);
//    }
//
//    @Test
//    void getMusic_NonExistingMusic_ReturnsNotFoundResponse() {
//        String token = "testToken";
//        String musicId = "123";
//
//        when(musicService.findMusic(musicId)).thenReturn(Optional.empty());
//
//        ResponseEntity<Music> response = musicController.getMusic(token, musicId);
//
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//        assertNull(response.getBody());
//        verifyNoMoreInteractions(userVisitedItemService);
//    }
//}
