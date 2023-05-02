package backend.main.repository;

import backend.main.model.entity.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteGenreMovieRepository extends CrudRepository<FavoriteGenreMovie, String> {
}
