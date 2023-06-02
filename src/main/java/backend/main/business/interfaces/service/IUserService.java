package backend.main.business.interfaces.service;

import backend.main.model.dto.UserAddDto;

public interface IUserService {
    String createUser(UserAddDto user);
}