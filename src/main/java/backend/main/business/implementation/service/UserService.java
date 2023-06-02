package backend.main.business.implementation.service;

import backend.main.business.interfaces.service.IUserService;
import backend.main.model.entity.User;
import backend.main.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;

    @Override
    public void createUser(User user) {
        String id = generateUniqueId();
        user.setId(id);
        user.setToken(generateRandomToken());
        userRepository.save(user);
    }

    private String generateUniqueId() {
        // TODO
        return "1111";
    }

    private String generateRandomToken() {
        // TODO
        return "sdhaigd8392";
    }
}