package backend.main.service;
import backend.main.business.implementation.service.PodcastService;
import backend.main.business.interfaces.parser.IFileParser;
import backend.main.business.interfaces.deserializer.IPodcastDeserializer;
import backend.main.business.interfaces.service.IPodcastService;
import backend.main.model.entity.Podcast;
import backend.main.repository.PodcastRepository;
import com.opencsv.exceptions.CsvValidationException;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PodcastServiceTest {
    private IPodcastService podcastService;
    @Mock
    private IFileParser fileParser;
    @Mock
    private IPodcastDeserializer podcastDeserializer;
    @Mock
    private PodcastRepository podcastRepository;

    @BeforeEach
    public void setUp() {
        fileParser = mock(IFileParser.class);
        podcastDeserializer = mock(IPodcastDeserializer.class);
        podcastRepository = mock(PodcastRepository.class);

        podcastService = new PodcastService(fileParser, podcastDeserializer, podcastRepository);
    }

    @Test
    public void importDataFromCSV_ValidFile_CallsFileParserAndPodcastDeserializer() throws IOException, CsvValidationException {
        MultipartFile file = new MockMultipartFile("test.csv", "CSV".getBytes());

        List<String[]> lines = Arrays.asList(
                new String[]{"1", "Podcast 1", "Description 1"},
                new String[]{"2", "Podcast 2", "Description 2"}
        );
        List<Podcast> podcasts = Arrays.asList(
                Podcast.builder().id("1").title("Podcast 1").description("Description 1").build(),
                Podcast.builder().id("2").title("Podcast 2").description("Description 2").build()
        );

        when(fileParser.parse(file)).thenReturn(lines);
        when(podcastDeserializer.deserialize(lines)).thenReturn(podcasts);

        int savedCount = podcastService.importDataFromCSV(file);

        verify(fileParser).parse(file);
        verify(podcastDeserializer).deserialize(lines);
        verify(podcastRepository).saveAll(podcasts);
        assertEquals(podcasts.size(), savedCount);
    }

    @Test
    public void importDataFromCSV_ExceptionThrown_ReturnsZeroSavedCount() throws IOException, CsvValidationException {
        MultipartFile file = new MockMultipartFile("test.csv", "CSV".getBytes());

        when(fileParser.parse(file)).thenThrow(IOException.class);

        int savedCount = podcastService.importDataFromCSV(file);

        verify(fileParser).parse(file);
        verifyNoMoreInteractions(podcastDeserializer);
        verifyNoMoreInteractions(podcastRepository);
        assertEquals(0, savedCount);
    }

    @Test
    public void findPodcast_PodcastExists_ReturnsOptionalPodcast() {
        String podcastId = "1";
        Podcast expectedPodcast = new Podcast();
        when(podcastRepository.findById(podcastId)).thenReturn(Optional.of(expectedPodcast));

        Optional<Podcast> podcast = podcastService.findPodcast(podcastId);

        verify(podcastRepository).findById(podcastId);
        assertEquals(Optional.of(expectedPodcast), podcast);
    }

    @Test
    public void findPodcast_PodcastNotExists_ReturnsEmptyOptional() {
        String podcastId = "1";
        when(podcastRepository.findById(podcastId)).thenReturn(Optional.empty());

        Optional<Podcast> podcast = podcastService.findPodcast(podcastId);

        verify(podcastRepository).findById(podcastId);
        assertEquals(Optional.empty(), podcast);
    }
}

