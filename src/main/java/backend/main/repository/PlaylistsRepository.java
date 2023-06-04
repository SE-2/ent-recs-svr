package backend.main.repository;

import backend.main.model.entity.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaylistsRepository extends CrudRepository<Playlists, Integer> {

    Playlists findByPlaylistIDAndUserID(Integer playlistId, String userId);

    List<Playlists> findByUserID(String userId);
}
