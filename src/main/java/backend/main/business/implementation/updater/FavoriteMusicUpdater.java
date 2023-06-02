package backend.main.business.implementation.updater;

import backend.main.business.interfaces.updater.IFavoriteMusicUpdater;
import backend.main.model.entity.*;
import backend.main.repository.FavoriteSingerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@RequiredArgsConstructor
@Component
public class FavoriteMusicUpdater implements IFavoriteMusicUpdater {
    private final FavoriteSingerRepository favoriteSingerRepository;

    @Override
    public void updateMusicFavorites(List<Music> musics, User user) {
        List<String> allSingers = new ArrayList<>();
        for (Music music : musics) {
            allSingers.add(music.getArtist());
        }

        for (String singer : allSingers) {
            FavoriteSinger favoriteSinger = favoriteSingerRepository.findByUserIDAndSinger(user.getId(), singer)
                    .orElse(new FavoriteSinger(user.getId(), singer, 0.0));

            favoriteSinger.setRate(favoriteSinger.getRate() + 1);

            favoriteSingerRepository.save(favoriteSinger);
        }
    }
}
