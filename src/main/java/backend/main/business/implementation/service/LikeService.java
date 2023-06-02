package backend.main.business.implementation.service;
import backend.main.business.interfaces.service.ILikeService;
import backend.main.model.entity.Like;
import backend.main.model.entity.User;
import backend.main.repository.LikeRepository;
import backend.main.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class LikeService implements ILikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;

    @Override
    public int likeMedia(String mediaId, String token) {
        User user = userRepository.findByToken(token);
        Like like = Like.builder()
                .userID(user.getId())
                .mediaId(mediaId)
                .date(LocalDateTime.now().toString())
                .build();

        likeRepository.save(like);

        return likeRepository.countByMediaId(mediaId);
    }
}