package backend.main.repository;

import backend.main.model.entity.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FavoritePodcastProducerRepository extends CrudRepository<FavoritePodcastProducer, String>  {
    Optional<FavoritePodcastProducer>findByUserIDAndProducer(String id, String producer);
}
