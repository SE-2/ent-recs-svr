package backend.main.business.interfaces.service;

import backend.main.model.dto.UserAddDto;
import backend.main.model.dto.UserFavoriteDto;
import backend.main.model.entity.User;

import java.util.List;

public interface IUserService {
    String createUser(UserAddDto user);
    User getUser(String token);

    void updateInterests(User user, UserFavoriteDto userFavoriteDto);
}