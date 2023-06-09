package backend.main.business.interfaces.service;

import backend.main.model.dto.MediaFilter;
import backend.main.model.dto.MediaType;
import backend.main.model.dto.SearchQuery;
import backend.main.model.entity.MediaMetadata;
import backend.main.model.entity.User;

import java.util.List;

public interface ISearchMediaService {
    List<MediaMetadata> search(User user, String query, MediaFilter mediaFilter);
    MediaType type();
}
