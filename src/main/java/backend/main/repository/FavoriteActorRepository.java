package backend.main.repository;

import backend.main.model.entity.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteActorRepository extends CrudRepository<FavoriteActor, String> {

    Optional<FavoriteActor> findByUserIDAndActor(String userId, String Actor);
    List<FavoriteActor> findByUserID(String userId);

}