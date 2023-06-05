package backend.main.repository;

import backend.main.model.entity.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserVisitedItemRepository extends CrudRepository<UserVisitedItem, String> {

    List<UserVisitedItem> findByUserIDOrderByDateDescTimeDesc(String userID);
}
