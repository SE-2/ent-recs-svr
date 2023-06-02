package backend.main.repository;

import backend.main.model.entity.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteGenrePodcastRepository extends CrudRepository<FavoriteGenrePodcast, String> {
    Optional<FavoriteGenrePodcast> findByUserIDAndGenre(String id, String genre);
    List<FavoriteGenrePodcast> findByUserID(String userId);
}
