package backend.main.repository;

import backend.main.model.entity.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteSingerRepository extends CrudRepository<FavoriteSinger, String> {

    Optional<FavoriteSinger> findByUserIDAndSinger(String id, String singer);
    List<FavoriteSinger> findByUserID(String userId);
}
