package backend.main.business.implementation.updater;

import backend.main.business.interfaces.updater.IFavoriteMovieUpdater;
import backend.main.model.entity.*;
import backend.main.repository.FavoriteActorRepository;
import backend.main.repository.FavoriteDirectorRepository;
import backend.main.repository.FavoriteGenreMovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Component
public class FavoriteMovieUpdater implements IFavoriteMovieUpdater {

    private final FavoriteGenreMovieRepository favoriteGenreMovieRepository;
    private final FavoriteDirectorRepository favoriteDirectorRepository;
    private final FavoriteActorRepository favoriteActorRepository;

    @Override
    public void updateMovieFavorites(List<Movie> movies, User user) {
        List<String> allGenres = new ArrayList<>();
        List<String> allDirectors = new ArrayList<>();
        List<String> allActors = new ArrayList<>();
        for (int i = 0; i < Math.min(3, movies.size()); i++) {
            Movie movie = movies.get(i);
            String[] genresArray = movie.getGenre().split(",\\s*");
            List<String> genres = Arrays.asList(genresArray);
            allGenres.addAll(genres);
            allDirectors.add(movie.getDirector());
            allActors.add(movie.getStar1());
            allActors.add(movie.getStar2());
            allActors.add(movie.getStar3());
            allActors.add(movie.getStar4());
        }

        for (String genre : allGenres) {
            FavoriteGenreMovie favoriteGenreMovie = favoriteGenreMovieRepository.findByUserIDAndGenre(user.getId(), genre)
                    .orElse(new FavoriteGenreMovie(user.getId(), genre, 0.0));

            favoriteGenreMovie.setRate(favoriteGenreMovie.getRate() + 1);

            favoriteGenreMovieRepository.save(favoriteGenreMovie);
        }

        for (String director : allDirectors) {
            FavoriteDirector favoriteDirector = favoriteDirectorRepository
                    .findByUserIDAndDirector(user.getId(), director)
                    .orElse(new FavoriteDirector(user.getId(), director, 0.0));

            favoriteDirector.setRate(favoriteDirector.getRate() + 1);

            favoriteDirectorRepository.save(favoriteDirector);
        }

        for (String actor : allActors) {
            FavoriteActor favoriteActor = favoriteActorRepository
                    .findByUserIDAndActor(user.getId(), actor)
                    .orElse(new FavoriteActor(user.getId(), actor, 0.0));

            favoriteActor.setRate(favoriteActor.getRate() + 1);

            favoriteActorRepository.save(favoriteActor);
        }
    }

    @Override
    public void updateMovieGenres(List<String> genres, User user) {
        for (String genre : genres) {
            FavoriteGenreMovie favoriteGenreMovie = favoriteGenreMovieRepository.findByUserIDAndGenre(user.getId(), genre)
                    .orElse(new FavoriteGenreMovie(user.getId(), genre, 0.0));

            favoriteGenreMovie.setRate(favoriteGenreMovie.getRate() + 1);

            favoriteGenreMovieRepository.save(favoriteGenreMovie);
        }
    }
}
