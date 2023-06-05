package backend.main.business.implementation.service;

import backend.main.business.interfaces.service.IUserService;
import backend.main.business.interfaces.updater.IFavoriteBookUpdater;
import backend.main.business.interfaces.updater.IFavoriteMovieUpdater;
import backend.main.business.interfaces.updater.IFavoriteMusicUpdater;
import backend.main.business.interfaces.updater.IFavoritePodcastUpdater;
import backend.main.model.dto.MediaType;
import backend.main.model.dto.UserAddDto;
import backend.main.model.dto.UserFavoriteDto;
import backend.main.model.dto.UserGetDto;
import backend.main.model.entity.User;
import backend.main.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService implements IUserService {
    private final IFavoriteBookUpdater iFavoriteBookUpdater;
    private final IFavoriteMusicUpdater iFavoriteMusicUpdater;
    private final IFavoriteMovieUpdater iFavoriteMovieUpdater;
    private final IFavoritePodcastUpdater iFavoritePodcastUpdater;
    private final UserRepository userRepository;

    @Override
    public String createUser(UserAddDto userDto) {
        User user = User.builder()
                .email(userDto.getEmail())
                .birthDate(userDto.getBirthDate())
                .token(userDto.getToken())
                .profileImgUrl(userDto.getProfileImgUrl())
                .name(userDto.getName())
                .book(false)
                .movie(false)
                .podcast(false)
                .music(false)
                .build();
        if (userRepository.findByToken(userDto.getToken()) == null)
            userRepository.save(user);
        return user.getId();
    }

    @Override
    public User getUser(String token) {
        return userRepository.findByToken(token);
    }

    @Override
    public void updateInterests(User user, UserFavoriteDto userFavoriteDto) {
        if (userFavoriteDto.getMediaType().equals(MediaType.BOOK)) {
            iFavoriteBookUpdater.updateBookGenres(userFavoriteDto.getGenres(), user);
            user.setBook(true);
            userRepository.save(user);
        } else if (userFavoriteDto.getMediaType().equals(MediaType.MUSIC)) {
            iFavoriteMusicUpdater.updateMusicGenres(userFavoriteDto.getGenres(), user);
            user.setMusic(true);
            userRepository.save(user);
        } else if (userFavoriteDto.getMediaType().equals(MediaType.MOVIE)) {
            iFavoriteMovieUpdater.updateMovieGenres(userFavoriteDto.getGenres(), user);
            user.setMusic(true);
            userRepository.save(user);
        } else if (userFavoriteDto.getMediaType().equals(MediaType.PODCAST)) {
            iFavoritePodcastUpdater.updatePodcastGenres(userFavoriteDto.getGenres(), user);
            user.setPodcast(true);
            userRepository.save(user);
        }
    }

    @Override
    public boolean getFilled(User user, MediaType mediaType) {
        if (mediaType.equals(MediaType.BOOK))
            return user.getBook();
        else if (mediaType.equals(MediaType.PODCAST))
            return user.getPodcast();
        else if (mediaType.equals(MediaType.MOVIE))
            return user.getMovie();
        else
            return user.getMusic();
    }
}