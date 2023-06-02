package backend.main.business.implementation.service.trend;

import backend.main.business.interfaces.service.ITrendMediaService;
import backend.main.model.entity.Like;
import backend.main.model.entity.MediaMetadata;
import backend.main.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TrendMediaService implements ITrendMediaService {
    private final LikeRepository likeRepository;

    @Override
    public List<MediaMetadata> getTodayTrendMedia() {
        LocalDate today = LocalDate.now();

//        List<String> itemIds = likeRepository.findByDate(today.toString())
//                .stream()
//                .map(Like::getMediaId)
//                .collect(Collectors.toList());

        return null;
    }

    @Override
    public List<MediaMetadata> getAllTimeTrendMedia() {
//        List<String> itemIds = likeRepository.findAll()
//                .stream()
//                .map(Like::getMediaId)
//                .distinct()
//                .collect(Collectors.toList());

        return null;
    }
}