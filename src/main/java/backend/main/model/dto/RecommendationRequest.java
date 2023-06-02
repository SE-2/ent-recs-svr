package backend.main.model.dto;

import lombok.AllArgsConstructor;

import java.util.Map;
@AllArgsConstructor
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
