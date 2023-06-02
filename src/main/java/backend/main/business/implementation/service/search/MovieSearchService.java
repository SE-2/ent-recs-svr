package backend.main.business.implementation.service.search;

import backend.main.business.interfaces.metadataConvertor.IMovieToMetadataConvertor;
import backend.main.business.interfaces.service.ISearchMediaService;
import backend.main.model.dto.MediaFilter;
import backend.main.model.dto.MediaType;
import backend.main.model.dto.SearchQuery;
import backend.main.model.entity.MediaMetadata;
import backend.main.model.entity.Movie;
import backend.main.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieSearchService implements ISearchMediaService {
    private final MovieRepository movieRepository;
    private final IMovieToMetadataConvertor movieConvertor;

    @Override
    public List<MediaMetadata> search(String query, MediaFilter mediaFilter) {
        String filter = join(mediaFilter.getCategories());

        List<Movie> movies;
        if (!filter.equals(""))
            movies = movieRepository.searchMovies(query, filter);
        else
            movies = movieRepository.searchMoviesWithoutFilter(query, filter);
        return movieConvertor.convertToMediaMetadata(movies);
    }

    @Override
    public MediaType type() {
        return MediaType.MOVIE;
    }

    public String join(List<String> categories) {
        if (!categories.isEmpty())
            return String.join(" OR ", categories);
        else return "";
    }
}
