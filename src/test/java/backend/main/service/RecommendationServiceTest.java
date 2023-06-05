package backend.main.service;
import backend.main.business.implementation.metadataConvertor.*;
import backend.main.business.implementation.service.*;
import backend.main.business.interfaces.service.*;
import backend.main.model.dto.*;
import backend.main.model.dto.MediaType;
import backend.main.model.entity.*;
import backend.main.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestTemplate;

import javax.print.attribute.standard.Media;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@TestPropertySource(properties = "ai.url=http://5.34.201.62:5000/api/similar_items")
public class RecommendationServiceTest {
    private IRecommendationService recommendationService;
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private UserRepository userRepository;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private MusicRepository musicRepository;
    @Mock
    private MovieRepository movieRepository;
    @Mock
    private PodcastRepository podcastRepository;
    @Mock
    private FavoriteActorRepository favoriteActorRepository;
    @Mock
    private FavoriteGenreBookRepository favoriteGenreBookRepository;
    @Mock
    private FavoriteGenreMovieRepository favoriteGenreMovieRepository;
    @Mock
    private FavoriteGenrePodcastRepository favoriteGenrePodcastRepository;
    @Mock
    private FavoritePodcastProducerRepository favoritePodcastProducerRepository;
    @Mock
    private FavoriteSingerRepository favoriteSingerRepository;
    @Mock
    private BookToMediaMetadataConverter bookToMediaMetadataConverter;
    @Mock
    private MovieToMediaMetadataConverter movieToMediaMetadataConverter;
    @Mock
    private MusicToMediaMetadataConverter musicToMediaMetadataConverter;
    @Mock
    private PodcastToMediaMetadataConverter podcastToMediaMetadataConverter;

    @BeforeEach
    public void setUp() {
        restTemplate = mock(RestTemplate.class);
        userRepository = mock(UserRepository.class);
        bookRepository = mock(BookRepository.class);
        musicRepository = mock(MusicRepository.class);
        movieRepository = mock(MovieRepository.class);
        podcastRepository = mock(PodcastRepository.class);
        favoriteActorRepository = mock(FavoriteActorRepository.class);
        favoriteGenreBookRepository = mock(FavoriteGenreBookRepository.class);
        favoriteGenreMovieRepository = mock(FavoriteGenreMovieRepository.class);
        favoriteGenrePodcastRepository = mock(FavoriteGenrePodcastRepository.class);
        favoritePodcastProducerRepository = mock(FavoritePodcastProducerRepository.class);
        favoriteSingerRepository = mock(FavoriteSingerRepository.class);
        bookToMediaMetadataConverter = mock(BookToMediaMetadataConverter.class);
        movieToMediaMetadataConverter = mock(MovieToMediaMetadataConverter.class);
        musicToMediaMetadataConverter = mock(MusicToMediaMetadataConverter.class);
        podcastToMediaMetadataConverter = mock(PodcastToMediaMetadataConverter.class);

        recommendationService = new RecommendationService(
                restTemplate,
                userRepository,
                bookRepository,
                musicRepository,
                movieRepository,
                podcastRepository,
                favoriteActorRepository,
                favoriteGenreBookRepository,
                favoriteGenreMovieRepository,
                favoriteGenrePodcastRepository,
                favoritePodcastProducerRepository,
                favoriteSingerRepository,
                bookToMediaMetadataConverter,
                movieToMediaMetadataConverter,
                musicToMediaMetadataConverter,
                podcastToMediaMetadataConverter
        );
    }

    @Test
    public void findRequestBody_ValidUserToken_ReturnsRecommendationRequest() {
        String userToken = "validToken";
        User user = new User();
        user.setId("1");

        List<FavoriteActor> favoriteActors = Collections.singletonList(new FavoriteActor("1","John Doe", 5.0));
        List<FavoriteGenreBook> favoriteGenreBooks = Collections.singletonList(new FavoriteGenreBook("1","Sci-Fi", 4.0));
        List<FavoriteGenreMovie> favoriteGenreMovies = Collections.singletonList(new FavoriteGenreMovie("1","Action", 3.0));
        List<FavoriteGenrePodcast> favoriteGenrePodcasts = Collections.singletonList(new FavoriteGenrePodcast("1","Technology", 2.0));
        List<FavoritePodcastProducer> favoritePodcastProducers = Collections.singletonList(new FavoritePodcastProducer("1","Tech Talks", 4.5));
        List<FavoriteSinger> favoriteMusicSingers = Collections.singletonList(new FavoriteSinger("1","Taylor Swift", 4.0));

        when(userRepository.findByToken(userToken)).thenReturn(user);
        when(favoriteActorRepository.findByUserID(user.getId())).thenReturn(favoriteActors);
        when(favoriteGenreBookRepository.findByUserID(user.getId())).thenReturn(favoriteGenreBooks);
        when(favoriteGenreMovieRepository.findByUserID(user.getId())).thenReturn(favoriteGenreMovies);
        when(favoriteGenrePodcastRepository.findByUserID(user.getId())).thenReturn(favoriteGenrePodcasts);
        when(favoritePodcastProducerRepository.findByUserID(user.getId())).thenReturn(favoritePodcastProducers);
        when(favoriteSingerRepository.findByUserID(user.getId())).thenReturn(favoriteMusicSingers);

        RecommendationRequest expectedRequest = new RecommendationRequest(
                MediaType.BOOK,
                Collections.singletonMap("Sci-Fi", 4.0),
                Collections.singletonMap("Technology", 2.0),
                Collections.singletonMap("Tech Talks", 4.5),
                Collections.singletonMap("Taylor Swift", 4.0),
                Collections.singletonMap("John Doe", 5.0),
                Collections.singletonMap("Action", 3.0)
        );

        RecommendationRequest actualRequest = recommendationService.findRequestBody(userToken);

        assertEquals(expectedRequest, actualRequest);
    }

    @Test
    public void getMedia_ValidMediaIds_ReturnsListOfMediaMetadata() {
        List<String> mediaIds = Arrays.asList("id1", "id2", "id3");

        List<Book> books = Collections.singletonList(new Book());
        List<Movie> movies = Collections.singletonList(new Movie());
        List<Podcast> podcasts = Collections.singletonList(new Podcast());
        List<Music> musics = Collections.singletonList(new Music());

        when(bookRepository.findBooksByIdIn(mediaIds)).thenReturn(books);
        when(movieRepository.findByIdIn(mediaIds)).thenReturn(movies);
        when(podcastRepository.findByIdIn(mediaIds)).thenReturn(podcasts);
        when(musicRepository.findByIdIn(mediaIds)).thenReturn(musics);

        List<MediaMetadata> expectedMetadata = Collections.singletonList(new MediaMetadata());

        when(bookToMediaMetadataConverter.convertToMediaMetadata(books)).thenReturn(expectedMetadata);

        List<MediaMetadata> actualMetadata = recommendationService.getMedia(MediaType.BOOK,mediaIds);

        assertEquals(expectedMetadata, actualMetadata);
    }
}

