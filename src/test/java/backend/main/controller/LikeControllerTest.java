package backend.main.controller;

import backend.main.business.interfaces.service.*;
import backend.main.controller.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class LikeControllerTest {

    @Mock
    private ILikeService likeService;

    private LikeController likeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        likeController = new LikeController(likeService);
    }

    @Test
    void likeMedia_ValidMediaId_ReturnsOkResponseWithLikesCount() {
        String token = "testToken";
        String mediaId = "123";
        int likesCount = 10;

        when(likeService.likeMedia(mediaId, token)).thenReturn(likesCount);

        ResponseEntity<Integer> response = likeController.likeMedia(token, mediaId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(likesCount, response.getBody());
        verify(likeService, times(1)).likeMedia(mediaId, token);
    }
}