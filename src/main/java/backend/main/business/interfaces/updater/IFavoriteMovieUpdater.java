package backend.main.business.interfaces.updater;

import backend.main.model.entity.Movie;
import backend.main.model.entity.User;

import java.util.List;

public interface IFavoriteMovieUpdater {
    void updateMovieFavorites(List<Movie> movies, User user);
}