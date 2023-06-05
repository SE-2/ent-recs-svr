//package backend.main.controller;
//
//import backend.main.business.interfaces.service.*;
//import backend.main.model.entity.*;
//import org.junit.jupiter.api.*;
//import org.mockito.*;
//import org.springframework.http.*;
//import org.springframework.mock.web.MockMultipartFile;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class PodcastControllerTest {
//
//    @Mock
//    private IPodcastService podcastService;
//
//    @Mock
//    private IUserVisitedItemService userVisitedItemService;
//
//    private PodcastController podcastController;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        podcastController = new PodcastController(podcastService, userVisitedItemService);
//    }
//
//    @Test
//    void importData_ValidFile_ReturnsCreatedResponse() {
//        MultipartFile file = new MockMultipartFile("podcast", "data.csv", "text/csv", "file content".getBytes());
//
//        int savedCount = 5;
//        when(podcastService.importDataFromCSV(file)).thenReturn(savedCount);
//
//        ResponseEntity<String> response = podcastController.importData(file);
//
//        assertEquals(HttpStatus.CREATED, response.getStatusCode());
//        assertEquals("Data imported successfully. " + savedCount, response.getBody());
//    }
//
//    @Test
//    void getPodcast_ExistingPodcast_ReturnsOkResponse() {
//        String token = "testToken";
//        String podcastId = "123";
//        Podcast podcast = new Podcast();
//
//        when(podcastService.findPodcast(podcastId)).thenReturn(Optional.of(podcast));
//
//        ResponseEntity<Podcast> response = podcastController.getPodcast(token, podcastId);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(podcast, response.getBody());
//        verify(userVisitedItemService, times(1)).saveItemVisited(podcast.getId(), token);
//    }
//
//    @Test
//    void getPodcast_NonExistingPodcast_ReturnsNotFoundResponse() {
//        String token = "testToken";
//        String podcastId = "123";
//
//        when(podcastService.findPodcast(podcastId)).thenReturn(Optional.empty());
//
//        ResponseEntity<Podcast> response = podcastController.getPodcast(token, podcastId);
//
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//        assertNull(response.getBody());
//    }
//}
