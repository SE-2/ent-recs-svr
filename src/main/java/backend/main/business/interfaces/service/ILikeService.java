package backend.main.business.interfaces.service;

public interface ILikeService {

    int likeMedia(String mediaId, String userID);
    String findUserID(String token);
    boolean isLiked(String mediaId, String token);
}
