package backend.main.business.implementation.service.sort;

import backend.main.business.interfaces.service.IMediaSortService;
import backend.main.model.dto.SortMethod;
import backend.main.model.entity.MediaMetadata;

import java.util.List;

public class MediaSortService implements IMediaSortService {
    @Override
    public List<MediaMetadata> sort(List<MediaMetadata> mediaList, SortMethod sortMethod) {
        // todo sort by likes count if sort method is popularity
       return mediaList;
    }
}
