package backend.main.business.implementation.service.request;

import backend.main.business.interfaces.request.IHttpRequestService;
import backend.main.model.dto.RecommendationRequest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

//public class HttpRequest implements IHttpRequestService {
//    @Override
//    public List<String> recommendationRequest(RecommendationRequest recommendationRequest) {
//        RecommendationRequest request = new RecommendationRequest();
//
//
//
//    }
//}
