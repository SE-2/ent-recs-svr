package backend.main.repository;

import backend.main.model.entity.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FavoriteDirectorRepository extends CrudRepository<FavoriteDirector, String> {
    Optional<FavoriteDirector> findByUserIDAndDirector(String userId, String director);
}
