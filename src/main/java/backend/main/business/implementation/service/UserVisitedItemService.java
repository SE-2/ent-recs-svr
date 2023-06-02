package backend.main.business.implementation.service;
import backend.main.business.interfaces.service.IUserVisitedItemService;
import backend.main.model.entity.User;
import backend.main.model.entity.UserVisitedItem;
import backend.main.repository.UserRepository;
import backend.main.repository.UserVisitedItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserVisitedItemService implements IUserVisitedItemService {

    private final UserVisitedItemRepository userVisitedItemRepository;
    private final UserRepository userRepository;

    public void saveItemVisited(String mediaId, String token) {
        String userID = findUserID(token);

        UserVisitedItem visitedItem = UserVisitedItem.builder()
                .userID(userID)
                .itemID(mediaId)
                .build();

        userVisitedItemRepository.save(visitedItem);
    }

    public String findUserID(String token) {
        User user = userRepository.findByToken(token);
        return user.getId();
    }
}
