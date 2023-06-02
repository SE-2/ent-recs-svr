package backend.main.business.interfaces.service;

import backend.main.model.entity.MediaMetadata;

import java.util.List;

public interface ITrendMediaService {
    List<MediaMetadata> getTodayTrendMedia();

    List<MediaMetadata> getAllTimeTrendMedia();
}
