package backend.main.business.interfaces.service;

import backend.main.model.dto.SearchQuery;
import backend.main.model.entity.MediaMetadata;

import java.util.List;

public interface IMediaMetadataService {
    List<MediaMetadata> getMediaMetadata(SearchQuery searchQuery);
}
