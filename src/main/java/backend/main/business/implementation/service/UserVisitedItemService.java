package backend.main.business.implementation.service;
import backend.main.business.implementation.metadataConvertor.BookToMediaMetadataConverter;
import backend.main.business.implementation.metadataConvertor.MovieToMediaMetadataConverter;
import backend.main.business.implementation.metadataConvertor.MusicToMediaMetadataConverter;
import backend.main.business.implementation.metadataConvertor.PodcastToMediaMetadataConverter;
import backend.main.business.interfaces.service.IUserVisitedItemService;
import backend.main.model.entity.*;
import backend.main.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserVisitedItemService implements IUserVisitedItemService {

    private final UserVisitedItemRepository userVisitedItemRepository;
    private final UserRepository userRepository;
    private final BookToMediaMetadataConverter bookToMediaMetadataConverter;
    private final MovieToMediaMetadataConverter movieToMediaMetadataConverter;
    private final MusicToMediaMetadataConverter musicToMediaMetadataConverter;
    private final PodcastToMediaMetadataConverter podcastToMediaMetadataConverter;
    private final MovieRepository movieRepository;
    private final BookRepository bookRepository;
    private final PodcastRepository podcastRepository;
    private final MusicRepository musicRepository;

    public void saveItemVisited(String mediaId, String token) {
        String userID = findUserID(token);

        UserVisitedItem visitedItem = UserVisitedItem.builder()
                .userID(userID)
                .itemID(mediaId)
                .date(LocalDate.now().toString())
                .time(LocalTime.now().toString())
                .build();

        userVisitedItemRepository.save(visitedItem);
    }

    @Override
    public List<MediaMetadata> getRecentVisitedItems(String token) {
        String userID = findUserID(token);
        List<UserVisitedItem> visitedItems = userVisitedItemRepository.findByUserIdOrderByDateDescTimeDesc(userID);

        List<Book> books = new ArrayList<>();
        List<Movie> movies = new ArrayList<>();
        List<Music> musics = new ArrayList<>();
        List<Podcast> podcasts = new ArrayList<>();
        List<MediaMetadata> res = new ArrayList<>();

        for (UserVisitedItem x: visitedItems) {
            MediaMetadata temp = null;
            if (x.getItemID().startsWith("S")) {
                Optional<Music> oMusic = musicRepository.findById(x.getItemID());
                oMusic.ifPresent(musics::add);
                List<MediaMetadata> list = musicToMediaMetadataConverter.convertToMediaMetadata(musics);
                temp = list.get(0);
                musics.clear();
            } else if (x.getItemID().startsWith("M")) {
                Optional<Movie> oMovie = movieRepository.findById(x.getItemID());
                oMovie.ifPresent(movies::add);
                List<MediaMetadata> list = movieToMediaMetadataConverter.convertToMediaMetadata(movies);
                temp = list.get(0);
                movies.clear();
            } else if (x.getItemID().startsWith("B")) {
                Optional<Book> oBook = bookRepository.findById(x.getItemID());
                oBook.ifPresent(books::add);
                List<MediaMetadata> list = bookToMediaMetadataConverter.convertToMediaMetadata(books);
                temp = list.get(0);
                books.clear();
            } else if (x.getItemID().startsWith("P")) {
                Optional<Podcast> oPodcast = podcastRepository.findById(x.getItemID());
                oPodcast.ifPresent(podcasts::add);
                List<MediaMetadata> list = podcastToMediaMetadataConverter.convertToMediaMetadata(podcasts);
                temp = list.get(0);
                podcasts.clear();
            }
            if (temp != null) res.add(temp);
        }

        return res;
    }

    public String findUserID(String token) {
        User user = userRepository.findByToken(token);
        return user.getId();
    }
}
