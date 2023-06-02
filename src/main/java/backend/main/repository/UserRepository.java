package backend.main.repository;

import backend.main.model.entity.*;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
        User findByToken(String token);
}
