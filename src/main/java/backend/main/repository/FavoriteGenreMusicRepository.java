package backend.main.repository;

import backend.main.model.entity.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FavoriteGenreMusicRepository extends CrudRepository<FavoriteGenreMusic, String> {
    Optional<FavoriteGenreMusic> findByUserIDAndGenre(String id, String genre);
}
