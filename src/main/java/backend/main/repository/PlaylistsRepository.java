package backend.main.repository;

import backend.main.model.entity.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaylistsRepository extends CrudRepository<Playlists, String> {

    Playlists findByPlaylistIDAndUserID(String playlistId, String userId);

    List<Playlists> findByUserId(String userId);
}
