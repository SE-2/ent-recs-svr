package backend.main.controller;
import backend.main.business.interfaces.service.*;
import backend.main.model.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PlaylistItemControllerTest {

    @Mock
    private IPlaylistItemService playlistItemService;

    @Mock
    private IUserService userService;

    private PlaylistItemController playlistItemController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        playlistItemController = new PlaylistItemController(playlistItemService, userService);
    }

    @Test
    void addItemToPlaylist_ValidRequest_ReturnsOkResponse() {
        String token = "testToken";
        String playlistId = "123";
        String mediaId = "456";

        User user = new User();
        when(userService.getUser(token)).thenReturn(user);

        ResponseEntity<String> response = playlistItemController.addItemToPlaylist(token, playlistId, mediaId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("item added to playlist successfully! ", response.getBody());
        verify(playlistItemService, times(1)).addItemToPlaylist(user.getId(), playlistId, mediaId);
    }

    @Test
    void deleteItemFromPlaylist_ValidRequest_ReturnsOkResponse() {
        String token = "testToken";
        String mediaId = "456";

        User user = new User();
        when(userService.getUser(token)).thenReturn(user);

        ResponseEntity<String> response = playlistItemController.deleteItemFromPlaylist(token, mediaId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("item deleted to playlist successfully! ", response.getBody());
        verify(playlistItemService, times(1)).deleteItemFromPlaylist(user.getId(), mediaId);
    }
}

