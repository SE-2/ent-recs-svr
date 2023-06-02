package backend.main.repository;

import backend.main.model.entity.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FavoriteWriterRepository extends CrudRepository<FavoriteWriter, String> {

    Optional<FavoriteWriter> findByUserIDAndWriter(String id, String writer);

}
