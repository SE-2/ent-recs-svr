package backend.main.business.interfaces.service;

import backend.main.model.dto.SortMethod;
import backend.main.model.entity.MediaMetadata;

import java.util.List;

public interface IMediaSortService {
    List<MediaMetadata> sort(List<MediaMetadata> mediaList, SortMethod sortMethod);
}
