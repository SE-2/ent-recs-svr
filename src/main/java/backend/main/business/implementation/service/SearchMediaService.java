package backend.main.business.implementation.service;

import backend.main.business.interfaces.metadataConvertor.*;
import backend.main.business.interfaces.service.ISearchMediaService;
import backend.main.model.dto.*;
import backend.main.model.entity.*;
import backend.main.repository.BookRepository;
import backend.main.repository.MovieRepository;
import backend.main.repository.MusicRepository;
import backend.main.repository.PodcastRepository;
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
    private final MovieRepository movieRepository;
    private final MusicRepository musicRepository;
    private final PodcastRepository podcastRepository;

    public List<MediaMetadata> getMediaMetadata(SearchQuery searchQuery) {

        String filter = join(searchQuery.getFilter().getCategories());

        if (searchQuery.getFilter().getMediaType() == MediaType.BOOK) {
            List<Book> books;
            if (!filter.equals(""))
                 books= bookRepository.searchBooksWithoutFilter(searchQuery.getQuery(), filter);
            else
                 books = bookRepository.searchBooks(searchQuery.getQuery(), filter);
            return BookConvertor.convertToMediaMetadata(books);

        } else if (searchQuery.getFilter().getMediaType() == MediaType.MOVIE) {
            List<Movie> movies;
            if (!filter.equals(""))
                movies = movieRepository.searchMovies(searchQuery.getQuery(), filter);
            else
                movies = movieRepository.searchMoviesWithoutFilter(searchQuery.getQuery(), filter);
            return MovieConvertor.convertToMediaMetadata(movies);
        } else if (searchQuery.getFilter().getMediaType() == MediaType.MUSIC) {

            List<Music> musics = musicRepository.searchMusics(searchQuery.getQuery(), filter);
            return MusicConvertor.convertToMediaMetadata(musics);

        } else if (searchQuery.getFilter().getMediaType() == MediaType.PODCAST) {
            List<Podcast> podcasts;
            if (!filter.equals(""))
                podcasts = podcastRepository.searchPodcasts(searchQuery.getQuery(), filter);
            else
                podcasts = podcastRepository.searchPodcastsWithoutFilter(searchQuery.getQuery(), filter);
            return PodcastConvertor.convertToMediaMetadata(podcasts);

        }
        else return null;
    }

    public String join(List<String> categories) {
        if (!categories.isEmpty())
            return String.join(" OR ", categories);
        else return "";
    }
}

