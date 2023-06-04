package backend.main.business.implementation.service.sort;

import backend.main.business.interfaces.service.IMediaSortService;
import backend.main.model.dto.SortMethod;
import backend.main.model.entity.MediaMetadata;
import backend.main.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MediaSortService implements IMediaSortService {
    private final LikeRepository likeRepository;

    @Override
    public List<MediaMetadata> sort(List<MediaMetadata> mediaList, SortMethod sortMethod) {
        if (sortMethod == SortMethod.POPULARITY) {
            List<String> mediaIds = mediaList.stream().map(MediaMetadata::getMediaId).collect(Collectors.toList());
            Map<String, Integer> likesCountMap = getLikesCountMap(mediaIds);
            mediaList.sort(Comparator.comparing(media -> likesCountMap.getOrDefault(media.getMediaId(), 0), Comparator.reverseOrder()));
        }
        return mediaList;
    }

    private Map<String, Integer> getLikesCountMap(List<String> mediaIds) {
        Map<String, Integer> likesCountMap = new HashMap<>();
        for (String mediaId : mediaIds) {
            int likesCount = likeRepository.countByMediaId(mediaId);
            likesCountMap.put(mediaId, likesCount);
        }
        return likesCountMap;
    }
}
