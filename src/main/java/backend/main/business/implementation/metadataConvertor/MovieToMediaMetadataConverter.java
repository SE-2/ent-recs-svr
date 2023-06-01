package backend.main.business.implementation.metadataConvertor;

import backend.main.business.interfaces.metadataConvertor.IMovieToMetadataConvertor;
import backend.main.model.entity.MediaMetadata;
import backend.main.model.entity.Movie;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MovieToMediaMetadataConverter implements IMovieToMetadataConvertor {
    @Override
    public List<MediaMetadata> convertToMediaMetadata(List<Movie> movieList) {
        List<MediaMetadata> mediaMetadataList = new ArrayList<>();

        for (Movie movie : movieList) {
            Map<String, String> properties = new HashMap<>();
            properties.put("productionYear", String.valueOf(movie.getReleasedYear()));
            properties.put("duration", movie.getRuntime());
            properties.put("genre", movie.getGenre());
            properties.put("director", movie.getDirector());

            MediaMetadata mediaMetadata = new MediaMetadata(
                    movie.getId(),
                    "movie",
                    movie.getSeriesTitle(),
                    movie.getPosterLink(),
                    properties
            );

            mediaMetadataList.add(mediaMetadata);
        }

        return mediaMetadataList;
    }
}
