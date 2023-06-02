package backend.main.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Map;
@AllArgsConstructor
//@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class RecommendationRequest {
    MediaType mediaType;

    // book
    Map<String, Double> favoriteBookGenres;

    // podcast
    Map<String, Double> favoritePodcastGenres;
    Map<String, Double> favoritePodcastProducers;

    // music
    Map<String, Double> favoriteMusicSigners;

    // movie
    Map<String, Double> favoriteMovieActors;
    Map<String, Double> favoriteMovieGenres;
}
