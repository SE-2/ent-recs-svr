package backend.main.business.implementation.service.search;

import backend.main.business.interfaces.metadataConvertor.IPodcastToMetadataConvertor;
import backend.main.business.interfaces.service.ISearchMediaService;
import backend.main.business.interfaces.updater.IFavoritePodcastUpdater;
import backend.main.model.dto.MediaFilter;
import backend.main.model.dto.MediaType;
import backend.main.model.entity.MediaMetadata;
import backend.main.model.entity.Podcast;
import backend.main.model.entity.User;
import backend.main.repository.PodcastRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PodcastSearchService implements ISearchMediaService {
    private final PodcastRepository podcastRepository;
    private final IPodcastToMetadataConvertor podcastConvertor;
    private final IFavoritePodcastUpdater favoritePodcastUpdater;

    @Override
    public List<MediaMetadata> search(User user, String query, MediaFilter mediaFilter) {
        String filter = join(mediaFilter.getCategories());

        List<Podcast> podcasts;
        if (!filter.equals(""))
            podcasts = podcastRepository.searchPodcasts(query, filter);
        else
            podcasts = podcastRepository.searchPodcastsWithoutFilter(query, filter);
        favoritePodcastUpdater.updatePodcastFavorites(podcasts, user);
        return podcastConvertor.convertToMediaMetadata(podcasts);
    }

    @Override
    public MediaType type() {
        return MediaType.PODCAST;
    }

    public String join(List<String> categories) {
        if (!categories.isEmpty())
            return String.join(" AND ", categories);
        else return "";
    }
}
