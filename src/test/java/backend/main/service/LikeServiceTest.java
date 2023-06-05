package backend.main.service;

import backend.main.business.implementation.service.LikeService;
import backend.main.business.interfaces.service.ILikeService;
import backend.main.model.entity.Like;
import backend.main.model.entity.User;
import backend.main.repository.LikeRepository;
import backend.main.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LikeServiceTest {
    @Mock
    private LikeRepository likeRepository;

    @Mock
    private UserRepository userRepository;

    private ILikeService likeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        likeService = new LikeService(likeRepository, userRepository);
    }

    @Test
    public void likeMedia_ValidMediaIdAndToken_ReturnsLikeCount() {
        String mockMediaId = "123";
        String mockToken = "token123";
        String mockUserID = "user123";
        LocalDate mockDate = LocalDate.now();

        User mockUser = new User();
        mockUser.setId(mockUserID);

        when(userRepository.findByToken(mockToken)).thenReturn(mockUser);
        when(likeRepository.countByMediaId(mockMediaId)).thenReturn(1);

        int likeCount = likeService.likeMedia(mockMediaId, mockToken);

        assertEquals(1, likeCount);
        verify(userRepository).findByToken(mockToken);
        verify(likeRepository).save(any(Like.class));
        verify(likeRepository).countByMediaId(mockMediaId);
    }

    @Test
    public void findUserID_ValidToken_ReturnsUserID() {
        String mockToken = "token123";
        String mockUserID = "user123";

        User mockUser = new User();
        mockUser.setId(mockUserID);

        when(userRepository.findByToken(mockToken)).thenReturn(mockUser);

        String userID = likeService.findUserID(mockToken);

        assertEquals(mockUserID, userID);
        verify(userRepository).findByToken(mockToken);
    }
}

