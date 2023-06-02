package backend.main.business.implementation.service;

import backend.main.business.interfaces.service.IUserService;
import backend.main.model.dto.UserAddDto;
import backend.main.model.entity.User;
import backend.main.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;

    @Override
    public String createUser(UserAddDto userDto) {
        User user = User.builder()
                .email(userDto.getEmail())
                .birthDate(userDto.getBirthDate())
                .token(userDto.getToken())
                .profileImgUrl(userDto.getProfileImgUrl())
                .name(userDto.getName())
                .build();

        userRepository.save(user);
        return user.getId();
    }
}