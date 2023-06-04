package backend.main.business.implementation.service.trend;

import backend.main.business.implementation.metadataConvertor.BookToMediaMetadataConverter;
import backend.main.business.implementation.metadataConvertor.MovieToMediaMetadataConverter;
import backend.main.business.implementation.metadataConvertor.MusicToMediaMetadataConverter;
import backend.main.business.implementation.metadataConvertor.PodcastToMediaMetadataConverter;
import backend.main.business.implementation.service.sort.MediaSortService;
import backend.main.business.interfaces.service.ITrendMediaService;
import backend.main.model.dto.SortMethod;
import backend.main.model.entity.*;
import backend.main.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class TrendMediaService implements ITrendMediaService {
    private final LikeRepository likeRepository;
    private final BookToMediaMetadataConverter bookToMediaMetadataConverter;
    private final MovieToMediaMetadataConverter movieToMediaMetadataConverter;
    private final MusicToMediaMetadataConverter musicToMediaMetadataConverter;
    private final PodcastToMediaMetadataConverter podcastToMediaMetadataConverter;
    private final MovieRepository movieRepository;
    private final BookRepository bookRepository;
    private final PodcastRepository podcastRepository;
    private final MusicRepository musicRepository;
    final private MediaSortService mediaSortService;

    @Override
    public List<MediaMetadata> getAllTimeTrendMedia() {
        List<MediaMetadata> trends = getAllMediaMetadata();
        mediaSortService.sort(trends, SortMethod.POPULARITY);

        List<MediaMetadata> res = new ArrayList<>();
        for (int i = 0; i < 10; i++) res.add(trends.get(i));

        return res;
    }

    @Override
    public List<MediaMetadata> getTodayTrendMedia() {
        List<MediaMetadata> trends = getAllMediaMetadata();

        List<String> mediaIds = trends.stream().map(MediaMetadata::getMediaId).toList();
        List<Like> likes = likeRepository.findAllByDate(LocalDate.now().toString());
        Map<String, Integer> likesCountMap = new HashMap<>();
        for (String mediaId : mediaIds) {
            likesCountMap.put(mediaId, 0);
        }
        for (Like like : likes) {
            if (mediaIds.contains(like.getMediaId())) {
                likesCountMap.put(like.getMediaId(), likesCountMap.getOrDefault(like.getMediaId(), 0) + 1);
            }
        }

        trends.sort(Comparator.comparing(media -> likesCountMap.getOrDefault(media.getMediaId(), 0), Comparator.reverseOrder()));

        List<MediaMetadata> res = new ArrayList<>();
        for (int i = 0; i < 10; i++) res.add(trends.get(i));

        return res;
    }

    private List<MediaMetadata> getAllMediaMetadata() {
        List<MediaMetadata> trends = new ArrayList<>();

        Iterable<Book> booksIterable = bookRepository.findAll();
        List<Book> booksList = new ArrayList<>();
        booksIterable.forEach(booksList::add);

        Iterable<Movie> moviesIterable = movieRepository.findAll();
        List<Movie> moviesList = new ArrayList<>();
        moviesIterable.forEach(moviesList::add);

        Iterable<Podcast> podcastsIterable = podcastRepository.findAll();
        List<Podcast> podcastsList = new ArrayList<>();
        podcastsIterable.forEach(podcastsList::add);

        Iterable<Music> musicIterable = musicRepository.findAll();
        List<Music> musicsList = new ArrayList<>();
        musicIterable.forEach(musicsList::add);

        List<MediaMetadata> bookMeta = bookToMediaMetadataConverter.convertToMediaMetadata(booksList);
        List<MediaMetadata> movieMeta = movieToMediaMetadataConverter.convertToMediaMetadata(moviesList);
        List<MediaMetadata> podcastMeta = podcastToMediaMetadataConverter.convertToMediaMetadata(podcastsList);
        List<MediaMetadata> musicMeta = musicToMediaMetadataConverter.convertToMediaMetadata(musicsList);

        trends.addAll(bookMeta);
        trends.addAll(movieMeta);
        trends.addAll(podcastMeta);
        trends.addAll(musicMeta);

        return trends;
    }
}