package backend.main.business.interfaces.metadataConvertor;

import backend.main.model.entity.MediaMetadata;
import backend.main.model.entity.Movie;

import java.util.List;

public interface IMovieToMetadataConvertor {
    List<MediaMetadata> convertToMediaMetadata(List<Movie> movies);
}
