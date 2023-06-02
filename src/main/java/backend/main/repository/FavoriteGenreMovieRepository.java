package backend.main.repository;

import backend.main.model.entity.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FavoriteGenreMovieRepository extends CrudRepository<FavoriteGenreMovie, String> {
    Optional<FavoriteGenreMovie> findByUserIDAndAndGenre(String id, String Genre);
}
