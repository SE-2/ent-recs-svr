package backend.main.business.interfaces.service;

import backend.main.model.entity.User;

public interface IPlaylistsService {
    void createPlaylist(User user, String name, String types);
}
