package backend.main.Interfaces;

import backend.main.model.entity.Movie;

import java.util.List;

public interface IMovieDeserializer {
    List<Movie> deserialize(List<String[]> lines);
}
