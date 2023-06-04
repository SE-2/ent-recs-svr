package backend.main.business.implementation.service;

import backend.main.business.implementation.metadataConvertor.BookToMediaMetadataConverter;
import backend.main.business.implementation.metadataConvertor.MovieToMediaMetadataConverter;
import backend.main.business.implementation.metadataConvertor.MusicToMediaMetadataConverter;
import backend.main.business.implementation.metadataConvertor.PodcastToMediaMetadataConverter;
import backend.main.business.interfaces.service.IRecommendationService;
import backend.main.model.dto.MediaType;
import backend.main.model.dto.RecommendationRequest;
import backend.main.model.entity.*;
import backend.main.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RequiredArgsConstructor
@Service
public class RecommendationService implements IRecommendationService {
    private final RestTemplate restTemplate;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final MusicRepository musicRepository;
    private final MovieRepository movieRepository;
    private final PodcastRepository podcastRepository;
    private final FavoriteActorRepository favoriteActorRepository;
    private final FavoriteGenreBookRepository favoriteGenreBookRepository;
    private final FavoriteGenreMovieRepository favoriteGenreMovieRepository;
    private final FavoriteGenrePodcastRepository favoriteGenrePodcastRepository;
    private final FavoritePodcastProducerRepository favoritePodcastProducerRepository;
    private final FavoriteSingerRepository favoriteSingerRepository;

    private final BookToMediaMetadataConverter bookToMediaMetadataConverter;
    private final MovieToMediaMetadataConverter movieToMediaMetadataConverter;
    private final MusicToMediaMetadataConverter musicToMediaMetadataConverter;
    private final PodcastToMediaMetadataConverter podcastToMediaMetadataConverter;

    @Value("${ai.url}")
    private String recommendationServiceUrl;

    @Override
    public RecommendationRequest findRequestBody(String userToken) {
        User user = userRepository.findByToken(userToken);
        Map<String, Double> favoriteMovieActors = new HashMap<>();
        Map<String, Double> favoriteGenreBooks = new HashMap<>();
        Map<String, Double> favoriteGenreMovies = new HashMap<>();
        Map<String, Double> favoriteGenrePodcasts = new HashMap<>();
        Map<String, Double> favoritePodcastProducers = new HashMap<>();
        Map<String, Double> favoriteMusicSingers = new HashMap<>();

        List<FavoriteActor> favoriteActors = favoriteActorRepository.findByUserID(user.getId());
        for (FavoriteActor actor : favoriteActors)
            favoriteMovieActors.put(actor.getActor(), actor.getRate());

        List<FavoriteGenreBook> favoriteBooks = favoriteGenreBookRepository.findByUserID(user.getId());
        for (FavoriteGenreBook genre : favoriteBooks)
            favoriteGenreBooks.put(genre.getGenre(), genre.getRate());

        List<FavoriteGenreMovie> favoriteMovies = favoriteGenreMovieRepository.findByUserID(user.getId());
        for (FavoriteGenreMovie genre : favoriteMovies)
            favoriteGenreMovies.put(genre.getGenre(), genre.getRate());

        List<FavoriteGenrePodcast> favoritePodcasts = favoriteGenrePodcastRepository.findByUserID(user.getId());
        for (FavoriteGenrePodcast genre : favoritePodcasts)
            favoriteGenrePodcasts.put(genre.getGenre(), genre.getRate());

        List<FavoritePodcastProducer> favoriteProducers = favoritePodcastProducerRepository.findByUserID(user.getId());
        for (FavoritePodcastProducer producer : favoriteProducers)
            favoritePodcastProducers.put(producer.getPodcastProducer(), producer.getRate());

        List<FavoriteSinger> favoriteSingers = favoriteSingerRepository.findByUserID(user.getId());
        for (FavoriteSinger singer : favoriteSingers)
            favoriteMusicSingers.put(singer.getSinger(), singer.getRate());

        return new RecommendationRequest(MediaType.BOOK, favoriteGenreBooks, favoriteGenrePodcasts, favoritePodcastProducers, favoriteMusicSingers, favoriteMovieActors, favoriteGenreMovies);
    }

    @Override
    public List<String> httpRequest(RecommendationRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
        HttpEntity<RecommendationRequest> requestEntity = new HttpEntity<>(request, headers);

        // Send the HTTP request
        ResponseEntity<List<String>> response = restTemplate.exchange(
                recommendationServiceUrl, HttpMethod.POST, requestEntity, new ParameterizedTypeReference<List<String>>() {
                });

        if (response.getStatusCode().is2xxSuccessful()) {
            List<String> Ids = response.getBody();
            System.out.println("Recommendation request sent successfully");
            return Ids;
        } else {
            System.out.println("Failed to send recommendation request");
            return null;
        }
    }

    @Override
    public List<MediaMetadata> getMedia(List<String> mediaIds) {
        List<MediaMetadata> mediaMetadataList = new ArrayList<>();


        // Retrieve media metadata for each ID using your repository or service
        List<Book> books = bookRepository.findBooksByIdIn(mediaIds);
        List<Movie> movies = movieRepository.findByIdIn(mediaIds);
        List<Podcast> podcasts = podcastRepository.findByIdIn(mediaIds);
        List<Music> musics = musicRepository.findByIdIn(mediaIds);
        if (!books.isEmpty())
            return bookToMediaMetadataConverter.convertToMediaMetadata(books);
        else if (!movies.isEmpty())
            return movieToMediaMetadataConverter.convertToMediaMetadata(movies);
        else if (!podcasts.isEmpty())
            return podcastToMediaMetadataConverter.convertToMediaMetadata(podcasts);
        else if (!musics.isEmpty())
            return musicToMediaMetadataConverter.convertToMediaMetadata(musics);
        else
            return null;
    }

    @Override
    public List<MediaMetadata> recommend(@RequestHeader("Token") String userToken, MediaType mediaType) {
        RecommendationRequest request = findRequestBody(userToken);
        request.setMediaType(mediaType);
        List<String> ids = httpRequest(request);
        return getMedia(ids);
    }
}
