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
        userRepository.save(user);
    }
}