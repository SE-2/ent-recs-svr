package backend.main.repository;

import backend.main.model.entity.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface PlaylistItemRepository extends CrudRepository<PlaylistItem, String> {
    List<PlaylistItem> findByPlaylistID(String playlistID);
    void deleteByUserIDAndItemID(String userId, String itemId);
    Optional<PlaylistItem> findByItemIDAndUserID(String itemId, String userId);
}
