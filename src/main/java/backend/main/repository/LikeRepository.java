package backend.main.repository;

import backend.main.model.entity.Like;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeRepository extends CrudRepository<Like, String> {

    int countByMediaId(String mediaId);

    List<Like> findAllByDate(String date);

    Optional<Like> findByMediaIdAndUserID(String mediaId, String userId);
}
