package backend.main.repository;

import backend.main.model.entity.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaylistItemRepository extends CrudRepository<PlaylistItem, String> {
    List<PlaylistItem> findByPlaylistID(String playlistID);
}
