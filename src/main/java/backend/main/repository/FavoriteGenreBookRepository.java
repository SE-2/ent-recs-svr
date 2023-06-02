package backend.main.repository;

import backend.main.model.entity.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FavoriteGenreBookRepository extends CrudRepository<FavoriteGenreBook, String> {
    Optional<FavoriteGenreBook> findByUserIDAndGenre(String id, String genre);
}
