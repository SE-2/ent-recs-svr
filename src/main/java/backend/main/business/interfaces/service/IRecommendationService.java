package backend.main.business.interfaces.service;

import backend.main.model.dto.MediaType;
import backend.main.model.dto.RecommendationRequest;
import backend.main.model.entity.MediaMetadata;

import java.util.List;

public interface IRecommendationService {
    RecommendationRequest findRequestBody(String userToken);
    List<String> httpRequest(RecommendationRequest recommendationRequest);
    List<MediaMetadata> getMedia(List<String> mediaIds);
    List<MediaMetadata> recommend(String userToken, MediaType mediaType);
}