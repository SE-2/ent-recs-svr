package backend.main.business.implementation.service;

import backend.main.business.interfaces.metadataConvertor.*;
import backend.main.business.interfaces.service.ISearchMediaService;
import backend.main.business.interfaces.updater.IFavoriteBookUpdater;
import backend.main.business.interfaces.updater.IFavoriteMovieUpdater;
import backend.main.business.interfaces.updater.IFavoriteMusicUpdater;
import backend.main.business.interfaces.updater.IFavoritePodcastUpdater;
import backend.main.model.dto.*;
import backend.main.model.entity.*;
import backend.main.repository.*;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class SearchMediaService implements ISearchMediaService {
    private final IBookToMetadataConvertor BookConvertor;
    private final IMovieToMetadataConvertor MovieConvertor;
    private final IMusicToMetadataConvertor MusicConvertor;
    private final IPodcastToMetadataConvertor PodcastConvertor;

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
    private final MusicRepository musicRepository;
    private final PodcastRepository podcastRepository;

    private final IFavoriteBookUpdater iFavoriteBookUpdater;
    private final IFavoriteMovieUpdater iFavoriteMovieUpdater;
    private final IFavoriteMusicUpdater iFavoriteMusicUpdater;
    private final IFavoritePodcastUpdater iFavoritePodcastUpdater;

    public List<MediaMetadata> getMediaMetadata(SearchQuery searchQuery) {
        String filter = join(searchQuery.getFilter().getCategories());
        User user = userRepository.findByToken(searchQuery.getUserToken());
        if (searchQuery.getFilter().getMediaType() == MediaType.BOOK) {
            List<Book> books;
            if (!filter.equals(""))
                books = bookRepository.searchBooksWithoutFilter(searchQuery.getQuery(), filter);
            else
                books = bookRepository.searchBooks(searchQuery.getQuery(), filter);
            iFavoriteBookUpdater.updateBookFavorites(books, user);
            return BookConvertor.convertToMediaMetadata(books);

        } else if (searchQuery.getFilter().getMediaType() == MediaType.MOVIE) {
            List<Movie> movies;
            if (!filter.equals(""))
                movies = movieRepository.searchMovies(searchQuery.getQuery(), filter);
            else
                movies = movieRepository.searchMoviesWithoutFilter(searchQuery.getQuery(), filter);
            iFavoriteMovieUpdater.updateMovieFavorites(movies, user);
            return MovieConvertor.convertToMediaMetadata(movies);
//-----------------------------------------------------------------------------

        } else if (searchQuery.getFilter().getMediaType() == MediaType.MUSIC) {
            List<Music> musics = musicRepository.searchMusics(searchQuery.getQuery(), filter);
            iFavoriteMusicUpdater.updateMusicFavorites(musics, user);
            return MusicConvertor.convertToMediaMetadata(musics);
//-----------------------------------------------------------------------------------
        } else if (searchQuery.getFilter().getMediaType() == MediaType.PODCAST) {
            List<Podcast> podcasts;
            if (!filter.equals(""))
                podcasts = podcastRepository.searchPodcasts(searchQuery.getQuery(), filter);
            else
                podcasts = podcastRepository.searchPodcastsWithoutFilter(searchQuery.getQuery(), filter);
            iFavoritePodcastUpdater.updatePodcastFavorites(podcasts, user);
            return PodcastConvertor.convertToMediaMetadata(podcasts);

        } else return null;
    }

    public String join(List<String> categories) {
        if (categories.size() > 1)
            return String.join(" OR ", categories);
        else if (categories.size() == 1)
            return String.join("", categories);
        else return "";
    }
}

