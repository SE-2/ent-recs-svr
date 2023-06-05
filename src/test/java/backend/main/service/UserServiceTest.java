package backend.main.service;

import backend.main.business.interfaces.updater.*;
import backend.main.model.dto.*;
import backend.main.model.entity.User;
import backend.main.repository.UserRepository;
import backend.main.business.implementation.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private IFavoriteBookUpdater favoriteBookUpdater;
    @Mock
    private IFavoriteMusicUpdater favoriteMusicUpdater;
    @Mock
    private IFavoriteMovieUpdater favoriteMovieUpdater;
    @Mock
    private IFavoritePodcastUpdater favoritePodcastUpdater;
    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(favoriteBookUpdater, favoriteMusicUpdater, favoriteMovieUpdater, favoritePodcastUpdater, userRepository);
    }

    @Test
    void getUser_ValidToken_UserReturned() {
        String token = "12345";
        User user = new User();
        user.setToken(token);

        when(userRepository.findByToken(token)).thenReturn(user);

        User result = userService.getUser(token);

        assertNotNull(result);
        assertEquals(user, result);
    }

    @Test
    void updateInterests_ValidUserAndUserFavoriteDto_InterestUpdated() {
        User user = new User();
        UserFavoriteDto userFavoriteDto = new UserFavoriteDto();
        userFavoriteDto.setMediaType(MediaType.BOOK);
        userFavoriteDto.setGenres(List.of("Fantasy", "Mystery"));

        userService.updateInterests(user, userFavoriteDto);

        assertTrue(user.getBook());
        verify(favoriteBookUpdater).updateBookGenres(userFavoriteDto.getGenres(), user);
        verify(userRepository).save(user);
    }

    @Test
    void getFilled_BookMediaType_ReturnsBookStatus() {
        User user = new User();
        user.setBook(true);

        boolean filled = userService.getFilled(user, MediaType.BOOK);

        assertTrue(filled);
    }

    @Test
    void updateInterests_ValidUserAndUserFavoriteDto_PodcastInterestUpdated() {
        User user = new User();
        UserFavoriteDto userFavoriteDto = new UserFavoriteDto();
        userFavoriteDto.setMediaType(MediaType.PODCAST);
        userFavoriteDto.setGenres(List.of("Comedy", "Technology"));

        userService.updateInterests(user, userFavoriteDto);

        assertTrue(user.getPodcast());
        verify(favoritePodcastUpdater).updatePodcastGenres(userFavoriteDto.getGenres(), user);
        verify(userRepository).save(user);
    }

    @Test
    void updateInterests_ValidUserAndUserFavoriteDto_MusicInterestUpdated() {
        User user = new User();
        UserFavoriteDto userFavoriteDto = new UserFavoriteDto();
        userFavoriteDto.setMediaType(MediaType.MUSIC);
        userFavoriteDto.setGenres(List.of("Rock", "Pop"));

        userService.updateInterests(user, userFavoriteDto);

        assertTrue(user.getMusic());
        verify(favoriteMusicUpdater).updateMusicGenres(userFavoriteDto.getGenres(), user);
        verify(userRepository).save(user);
    }

    @Test
    void getFilled_PodcastMediaType_ReturnsPodcastStatus() {
        User user = new User();
        user.setPodcast(true);

        boolean filled = userService.getFilled(user, MediaType.PODCAST);

        assertTrue(filled);
    }

    @Test
    void getFilled_MovieMediaType_ReturnsMovieStatus() {
        User user = new User();
        user.setMovie(true);

        boolean filled = userService.getFilled(user, MediaType.MOVIE);

        assertTrue(filled);
    }

    @Test
    void getFilled_MusicMediaType_ReturnsMusicStatus() {
        User user = new User();
        user.setMusic(true);

        boolean filled = userService.getFilled(user, MediaType.MUSIC);

        assertTrue(filled);
    }


}

