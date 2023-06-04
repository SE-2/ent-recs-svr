package backend.main.business.implementation.service;

import backend.main.business.interfaces.service.IPlaylistsService;
import backend.main.model.entity.PlaylistItem;
import backend.main.model.entity.Playlists;
import backend.main.model.entity.User;
import backend.main.repository.PlaylistItemRepository;
import backend.main.repository.PlaylistsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PlaylistsService implements IPlaylistsService {
    private final PlaylistsRepository playlistsRepository;
    private final PlaylistItemRepository playlistItemRepository;

    @Override
    public void createPlaylist(User user, String name, String types) {
        Playlists playlist = new Playlists(user.getId(), name, LocalDateTime.now().toString(), "",types);
        playlistsRepository.save(playlist);
    }

    @Override
    public void deletePlaylist(String playlistID, User user) {
        Playlists playlist = playlistsRepository.findByPlaylistIDAndUserID(playlistID, user.getId());
        if (playlist == null) {
            throw new RuntimeException("PlayList Not Found");
        }
        playlistsRepository.delete(playlist);
    }

    public List<Playlists> getAllPlaylistsByUserId(String userId) {
        return playlistsRepository.findByUserId(userId);
    }


    public List<PlaylistItem> getPlaylistItems(String playlistId) {
        return playlistItemRepository.findByPlaylistID(playlistId);
    }
}
