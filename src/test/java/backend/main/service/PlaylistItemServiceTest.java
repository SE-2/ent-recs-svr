package backend.main.service;
import backend.main.business.implementation.service.PlaylistItemService;
import backend.main.business.interfaces.service.IPlaylistItemService;
import backend.main.model.entity.PlaylistItem;
import backend.main.repository.PlaylistItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PlaylistItemServiceTest {

    @Mock
    private PlaylistItemRepository playlistItemRepository;

    private IPlaylistItemService playlistItemService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        playlistItemService = new PlaylistItemService(playlistItemRepository);
    }

    @Test
    public void deleteItemFromPlaylist_ValidData_CallsDeleteByUserIDAndItemIDMethod() {
        String mockUserId = "user1";
        String mockMediaId = "media1";

        playlistItemService.deleteItemFromPlaylist(mockUserId, mockMediaId);

        verify(playlistItemRepository, times(1)).deleteByUserIDAndItemID(mockUserId, mockMediaId);
    }
}

