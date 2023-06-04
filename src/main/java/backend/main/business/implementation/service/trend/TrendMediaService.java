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
        return new ArrayList<>();
    }

    @Override
    public List<MediaMetadata> getAllTimeTrendMedia() {
        return new ArrayList<>();
    }
}