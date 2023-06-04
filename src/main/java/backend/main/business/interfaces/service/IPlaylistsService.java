package backend.main.business.interfaces.service;

import backend.main.model.entity.MediaMetadata;
import backend.main.model.entity.Playlists;
import backend.main.model.entity.User;

import java.util.List;

public interface IPlaylistsService {
    void createPlaylist(User user, String name, String types);

    void deletePlaylist(Integer playlistID, User user);

    List<Playlists> getAllPlaylistsByUserId(String userId);

    List<MediaMetadata> getPlaylistItems(String playlistId);
}
