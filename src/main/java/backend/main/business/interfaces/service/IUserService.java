package backend.main.business.interfaces.service;

import backend.main.model.dto.MediaType;
import backend.main.model.dto.UserAddDto;
import backend.main.model.dto.UserFavoriteDto;
import backend.main.model.entity.User;

public interface IUserService {
    String createUser(UserAddDto user);
    User getUser(String token);

    void updateInterests(User user, UserFavoriteDto userFavoriteDto);

    boolean getFilled(User user, MediaType mediaType);
}