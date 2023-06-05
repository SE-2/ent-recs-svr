package backend.main.business.interfaces.service;

import backend.main.model.entity.MediaMetadata;

import java.util.List;

public interface IUserVisitedItemService {

    void saveItemVisited(String mediaId, String token);

    List<MediaMetadata> getRecentVisitedItems(String token);
}
