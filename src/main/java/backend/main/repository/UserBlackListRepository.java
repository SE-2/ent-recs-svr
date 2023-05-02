package backend.main.repository;

import backend.main.model.entity.UserBlackList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBlackListRepository extends CrudRepository<UserBlackList, String> {
}
