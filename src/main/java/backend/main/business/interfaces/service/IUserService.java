package backend.main.business.interfaces.service;

import backend.main.model.dto.UserAddDto;
import backend.main.model.dto.UserGetDto;

public interface IUserService {
    String createUser(UserAddDto user);
    UserGetDto getUser(String token);
}