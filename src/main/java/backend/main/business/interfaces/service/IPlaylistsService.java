package backend.main.business.interfaces.service;

import backend.main.model.entity.PlaylistItem;
import backend.main.model.entity.Playlists;
import backend.main.model.entity.User;

import java.util.List;

public interface IPlaylistsService {
    void createPlaylist(User user, String name, String types);

    void deletePlaylist(String playlistID);

    List<Playlists> getAllPlaylistsByUserId(String userId);

    List<PlaylistItem> getPlaylistItems(String playlistId);
}
