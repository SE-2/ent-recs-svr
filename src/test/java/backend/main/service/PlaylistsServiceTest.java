package backend.main.service;
import backend.main.business.implementation.metadataConvertor.*;
import backend.main.business.implementation.service.*;
import backend.main.business.interfaces.service.IPlaylistsService;
import backend.main.model.entity.*;
import backend.main.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PlaylistsServiceTest {
    private IPlaylistsService playlistsService;
    private PlaylistsRepository playlistsRepository;
    private PlaylistItemRepository playlistItemRepository;
    private BookToMediaMetadataConverter bookToMediaMetadataConverter;
    private MovieToMediaMetadataConverter movieToMediaMetadataConverter;
    private MusicToMediaMetadataConverter musicToMediaMetadataConverter;
    private PodcastToMediaMetadataConverter podcastToMediaMetadataConverter;
    private MovieRepository movieRepository;
    private BookRepository bookRepository;
    private PodcastRepository podcastRepository;
    private MusicRepository musicRepository;

    @BeforeEach
    public void setUp() {
        playlistsRepository = mock(PlaylistsRepository.class);
        playlistItemRepository = mock(PlaylistItemRepository.class);
        bookToMediaMetadataConverter = mock(BookToMediaMetadataConverter.class);
        movieToMediaMetadataConverter = mock(MovieToMediaMetadataConverter.class);
        musicToMediaMetadataConverter = mock(MusicToMediaMetadataConverter.class);
        podcastToMediaMetadataConverter = mock(PodcastToMediaMetadataConverter.class);
        movieRepository = mock(MovieRepository.class);
        bookRepository = mock(BookRepository.class);
        podcastRepository = mock(PodcastRepository.class);
        musicRepository = mock(MusicRepository.class);

        playlistsService = new PlaylistsService(playlistsRepository, playlistItemRepository, bookToMediaMetadataConverter,
                movieToMediaMetadataConverter, musicToMediaMetadataConverter, podcastToMediaMetadataConverter,
                movieRepository, bookRepository, podcastRepository, musicRepository);
    }

    @Test
    public void deletePlaylist_PlaylistExists_CallsDeleteMethod() {
        Integer playlistId = 1;
        User user = new User();
        Playlists playlist = new Playlists();
        playlist.setPlaylistID(playlistId);
        playlist.setUserID(user.getId());

        when(playlistsRepository.findByPlaylistIDAndUserID(playlistId, user.getId())).thenReturn(Optional.of(playlist));

        playlistsService.deletePlaylist(playlistId, user);

        verify(playlistsRepository).delete(playlist);
    }

    @Test
    public void deletePlaylist_PlaylistNotExists_ThrowsException() {
        Integer playlistId = 1;
        User user = new User();

        when(playlistsRepository.findByPlaylistIDAndUserID(playlistId, user.getId())).thenReturn(null);

        RuntimeException exception = org.junit.jupiter.api.Assertions.assertThrows(
                RuntimeException.class,
                () -> playlistsService.deletePlaylist(playlistId, user)
        );

        assertEquals("PlayList Not Found", exception.getMessage());
    }

    @Test
    public void getAllPlaylistsByUserId_ValidUserId_ReturnsListOfPlaylists() {
        String userId = "user123";
        List<Playlists> expectedPlaylists = new ArrayList<>();
        expectedPlaylists.add(new Playlists());
        expectedPlaylists.add(new Playlists());

        when(playlistsRepository.findByUserID(userId)).thenReturn(expectedPlaylists);

        List<Playlists> playlists = playlistsService.getAllPlaylistsByUserId(userId);

        assertEquals(expectedPlaylists, playlists);
    }

    @Test
    public void getPlaylistItems_ValidPlaylistId_ReturnsListOfMediaMetadata() {
        String playlistId = "playlist123";
        List<PlaylistItem> playlistItems = new ArrayList<>();
        playlistItems.add(new PlaylistItem("userId1", playlistId, "S1"));
        playlistItems.add(new PlaylistItem("userId2", playlistId, "M1"));
        playlistItems.add(new PlaylistItem("userId3", playlistId, "B1"));
        playlistItems.add(new PlaylistItem("userId4", playlistId, "P1"));

        List<Book> books = new ArrayList<>();
        books.add(new Book());
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie());
        List<Music> musics = new ArrayList<>();
        musics.add(new Music());
        List<Podcast> podcasts = new ArrayList<>();
        podcasts.add(new Podcast());

        List<MediaMetadata> expectedMetadata = new ArrayList<>();
        expectedMetadata.add(new MediaMetadata());
        expectedMetadata.add(new MediaMetadata());
        expectedMetadata.add(new MediaMetadata());
        expectedMetadata.add(new MediaMetadata());

        when(playlistItemRepository.findByPlaylistID(playlistId)).thenReturn(playlistItems);
        when(musicRepository.findById("S1")).thenReturn(Optional.of(musics.get(0)));
        when(movieRepository.findById("M1")).thenReturn(Optional.of(movies.get(0)));
        when(bookRepository.findById("B1")).thenReturn(Optional.of(books.get(0)));
        when(podcastRepository.findById("P1")).thenReturn(Optional.of(podcasts.get(0)));
        when(bookToMediaMetadataConverter.convertToMediaMetadata(books)).thenReturn(expectedMetadata.subList(0, 1));
        when(movieToMediaMetadataConverter.convertToMediaMetadata(movies)).thenReturn(expectedMetadata.subList(1, 2));
        when(podcastToMediaMetadataConverter.convertToMediaMetadata(podcasts)).thenReturn(expectedMetadata.subList(2, 3));
        when(musicToMediaMetadataConverter.convertToMediaMetadata(musics)).thenReturn(expectedMetadata.subList(3, 4));

        List<MediaMetadata> metadata = playlistsService.getPlaylistItems(playlistId);

        assertEquals(expectedMetadata, metadata);
    }
}

