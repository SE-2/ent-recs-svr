package backend.main.controller;

import backend.main.business.interfaces.service.IRecommendationService;
import backend.main.model.dto.MediaType;
import backend.main.model.entity.MediaMetadata;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RecommendationControllerTest {

    @Mock
    private IRecommendationService recommendationService;

    private RecommendationController recommendationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
        recommendationController = new RecommendationController(recommendationService);
    }

    @Test
    void processRecommendation_ValidMediaType_ReturnsOkResponse() {
        String userToken = "testToken";
        MediaType mediaType = MediaType.MOVIE;
        List<MediaMetadata> expectedResponse = new ArrayList<>();

        when(recommendationService.recommend(userToken, mediaType)).thenReturn(expectedResponse);

        ResponseEntity<List<MediaMetadata>> response = recommendationController.processRecommendation(userToken, mediaType);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }
}

