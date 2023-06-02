package backend.main.business.interfaces.request;

import backend.main.model.dto.RecommendationRequest;

import java.util.List;

public interface IHttpRequestService {
    List<String> recommendationRequest(RecommendationRequest recommendationRequest);
}
