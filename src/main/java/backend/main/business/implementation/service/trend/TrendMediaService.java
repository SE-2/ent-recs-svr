package backend.main.business.implementation.service.trend;

import backend.main.business.implementation.service.sort.MediaSortService;
import backend.main.business.interfaces.service.ITrendMediaService;
import backend.main.model.dto.SortMethod;
import backend.main.model.entity.Like;
import backend.main.model.entity.MediaMetadata;
import backend.main.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TrendMediaService implements ITrendMediaService {
    private final LikeRepository likeRepository;
    private final MediaSortService mediaSortService;

    @Override
    public List<MediaMetadata> getTodayTrendMedia() {
        LocalDate today = LocalDate.now();
        List<Like> likes = likeRepository.findAllByDate(today.toString());

        mediaSortService.sort(likes,SortMethod.POPULARITY);

        // Sort media by like count
        List<MediaMetadata> sortedMedia = likesCountMap.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .map(entry -> mediaSortService.getMediaById(entry.getKey()))
                .collect(Collectors.toList());

        return sortedMedia;
    }

    @Override
    public List<MediaMetadata> getAllTimeTrendMedia() {
        // Get all media metadata
        List<MediaMetadata> allMedia = mediaSortService.getAllMedia();

        // Map media IDs to their like counts
        Map<String, Integer> likesCountMap = new HashMap<>();
        for (MediaMetadata media : allMedia) {
            String mediaId = media.getMediaId();
            int count = likeRepository.countByMediaId(mediaId);
            likesCountMap.put(mediaId, count);
        }

        // Sort media by like count
        List<MediaMetadata> sortedMedia = likesCountMap.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .map(entry -> mediaSortService.getMediaById(entry.getKey()))
                .collect(Collectors.toList());

        return sortedMedia;
    }
}