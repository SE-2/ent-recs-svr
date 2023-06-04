package backend.main.business.implementation.service;

import backend.main.business.interfaces.service.IPlaylistsService;
import backend.main.model.entity.Playlists;
import backend.main.model.entity.User;
import backend.main.repository.PlaylistsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.*;

@RequiredArgsConstructor
@Service
public class PlaylistsService implements IPlaylistsService {
    private final PlaylistsRepository playlistsRepository;

    @Override
    public void createPlaylist(User user, String name, String types) {
        Playlists playlist = new Playlists(user.getId(), name, LocalDateTime.now().toString(), "",types);
        playlistsRepository.save(playlist);
    }
}
