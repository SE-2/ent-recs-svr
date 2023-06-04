package backend.main.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecommendationRequest {
    @JsonProperty("mediaType")
    MediaType mediaType;

    // book
    @JsonProperty("favoriteBookGenres")
    Map<String, Double> favoriteBookGenres;

    // podcast
    @JsonProperty("favoritePodcastGenres")
    Map<String, Double> favoritePodcastGenres;
    @JsonProperty("favoritePodcastProducers")
    Map<String, Double> favoritePodcastProducers;

    // music
    @JsonProperty("favoriteMusicSingers")
    Map<String, Double> favoriteMusicSigners;

    // movie
    @JsonProperty("favoriteMovieActors")
    Map<String, Double> favoriteMovieActors;
    @JsonProperty("favoriteMovieGenres")
    Map<String, Double> favoriteMovieGenres;
}