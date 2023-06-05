package backend.main.service;

import backend.main.business.implementation.service.MusicService;
import backend.main.business.interfaces.deserializer.IMusicDeserializer;
import backend.main.business.interfaces.parser.IFileParser;
import backend.main.model.entity.Music;
import backend.main.repository.MusicRepository;
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

public class MusicServiceTest {
    @Mock
    private IFileParser fileParser;

    @Mock
    private IMusicDeserializer musicDeserializer;

    @Mock
    private MusicRepository musicRepository;

    private MusicService musicService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        musicService = new MusicService(fileParser, musicDeserializer, musicRepository);
    }

    @Test
    public void importDataFromCSV_ValidFile_ReturnsSavedCount() throws IOException, CsvValidationException {
        MultipartFile mockFile = new MockMultipartFile("data.csv", "content".getBytes());
        List<String[]> mockLines = Arrays.asList(new String[]{"1", "Music 1"}, new String[]{"2", "Music 2"});
        List<Music> mockMusics = Arrays.asList(Music.builder().id("1").title("music 1").build(), Music.builder().id("2").title("music 2").build());

        when(fileParser.parse(mockFile)).thenReturn(mockLines);
        when(musicDeserializer.deserialize(mockLines)).thenReturn(mockMusics);
        when(musicRepository.saveAll(mockMusics)).thenReturn(mockMusics);

        int savedCount = musicService.importDataFromCSV(mockFile);

        assertEquals(mockMusics.size(), savedCount);
        verify(fileParser).parse(mockFile);
        verify(musicDeserializer).deserialize(mockLines);
        verify(musicRepository).saveAll(mockMusics);
    }

    @Test
    public void findMusic_ExistingId_ReturnsMusic() {
        String mockMusicId = "1";
        Music mockMusic = Music.builder().id(mockMusicId).title("music 1").build();

        when(musicRepository.findById(mockMusicId)).thenReturn(Optional.of(mockMusic));

        Optional<Music> foundMusic = musicService.findMusic(mockMusicId);

        assertEquals(Optional.of(mockMusic), foundMusic);
        verify(musicRepository).findById(mockMusicId);
    }

    @Test
    public void findMusic_NonExistingId_ReturnsEmptyOptional() {
        String mockMusicId = "1";

        when(musicRepository.findById(mockMusicId)).thenReturn(Optional.empty());

        Optional<Music> foundMusic = musicService.findMusic(mockMusicId);

        assertEquals(Optional.empty(), foundMusic);
        verify(musicRepository).findById(mockMusicId);
    }
}

