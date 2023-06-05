package backend.main.business.interfaces.service;

public interface IPlaylistItemService {
    void addItemToPlaylist(String userId,String playlistId, String mediaId);
    void deleteItemFromPlaylist(String userId, String mediaId);

    boolean isBookmarked(String mediaId, String userId);
}
