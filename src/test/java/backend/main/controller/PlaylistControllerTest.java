package backend.main.controller;
import backend.main.business.interfaces.service.*;
import backend.main.model.dto.*;
import backend.main.model.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PlaylistControllerTest {

    @Mock
    private IPlaylistsService playlistsService;

    @Mock
    private IUserService userService;

    private PlaylistController playlistController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        playlistController = new PlaylistController(playlistsService, userService);
    }

    @Test
    void createPlaylist_ValidRequest_ReturnsOkResponse() {
        String token = "testToken";
        CreatePlaylistDto createPlaylistDto = new CreatePlaylistDto("My Playlist", "MUSIC, MOVIE");

        User user = new User();
        when(userService.getUser(token)).thenReturn(user);

        ResponseEntity<String> response = playlistController.createPlaylist(token, createPlaylistDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("playlist created successfully! ", response.getBody());
        verify(playlistsService, times(1)).createPlaylist(user, createPlaylistDto.getName(), createPlaylistDto.getMediaTypes());
    }

    @Test
    void deletePlaylist_ValidRequest_ReturnsOkResponse() {
        String token = "testToken";
        int playlistID = 1;

        User user = new User();
        when(userService.getUser(token)).thenReturn(user);

        ResponseEntity<String> response = playlistController.deletePlaylist(token, playlistID);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("playlist deleted successfully! ", response.getBody());
        verify(playlistsService, times(1)).deletePlaylist(playlistID, user);
    }

    @Test
    void showAllPlaylists_ValidRequest_ReturnsPlaylists() {
        String token = "testToken";
        List<Playlists> playlists = Arrays.asList(new Playlists(), new Playlists());

        when(playlistsService.getAllPlaylistsByUserId(token)).thenReturn(playlists);

        ResponseEntity<List<Playlists>> response = playlistController.showAllPlaylists(token);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(playlists, response.getBody());
    }

    @Test
    void showPlaylistItems_ValidRequest_ReturnsPlaylistItems() {
        // Arrange
        String token = "testToken";
        String playlistID = "123";
        User user = new User();
        List<MediaMetadata> items = Arrays.asList(new MediaMetadata(), new MediaMetadata());

        when(userService.getUser(token)).thenReturn(user);

        when(playlistsService.getPlaylistItems(playlistID)).thenReturn(items);

        ResponseEntity<List<MediaMetadata>> response = playlistController.showPlaylistItems(token, playlistID);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(items, response.getBody());
    }
}

