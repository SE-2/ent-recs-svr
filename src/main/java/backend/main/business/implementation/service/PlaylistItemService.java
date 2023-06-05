package backend.main.business.implementation.service;

import backend.main.business.interfaces.service.IPlaylistItemService;
import backend.main.model.entity.PlaylistItem;
import backend.main.model.entity.Playlists;
import backend.main.repository.PlaylistItemRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.*;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PlaylistItemService implements IPlaylistItemService {
    private final PlaylistItemRepository playlistItemRepository;

    @Override
    public void addItemToPlaylist(String userId,String playlistId, String mediaId) {
        PlaylistItem playlistItem = new PlaylistItem(userId,playlistId,mediaId);
        playlistItemRepository.save(playlistItem);
    }
    @Transactional
    @Override
    public void deleteItemFromPlaylist(String userId, String mediaId) {
        playlistItemRepository.deleteByUserIDAndItemID(userId, mediaId);
    }

    @Override
    public boolean isBookmarked(String mediaId, String userId) {
        return playlistItemRepository.findByItemIDAndUserID(mediaId, userId).isPresent();
    }
}
