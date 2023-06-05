package backend.main.business.implementation.updater;

import backend.main.business.interfaces.updater.IFavoriteMusicUpdater;
import backend.main.model.entity.*;
import backend.main.repository.FavoriteGenreMusicRepository;
import backend.main.repository.FavoriteSingerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@RequiredArgsConstructor
@Component
public class FavoriteMusicUpdater implements IFavoriteMusicUpdater {
    private final FavoriteSingerRepository favoriteSingerRepository;
    private final FavoriteGenreMusicRepository favoriteGenreMusicRepository;

    @Override
    public void updateMusicFavorites(List<Music> musics, User user) {
        List<String> allSingers = new ArrayList<>();
        for (int i = 0; i < Math.min(3, musics.size()); i++) {
            Music music = musics.get(i);
            allSingers.add(music.getArtist());
        }

        for (String singer : allSingers) {
            FavoriteSinger favoriteSinger = favoriteSingerRepository.findByUserIDAndSinger(user.getId(), singer)
                    .orElse(new FavoriteSinger(user.getId(), singer, 0.0));

            favoriteSinger.setRate(favoriteSinger.getRate() + 1);

            favoriteSingerRepository.save(favoriteSinger);
        }
    }
    @Override
    public void updateMusicGenres(List<String> genres, User user) {


        for (String genre : genres) {
            FavoriteGenreMusic favoriteGenreMusic = favoriteGenreMusicRepository.findByUserIDAndGenre(user.getId(), genre)
                    .orElse(new FavoriteGenreMusic(user.getId(), genre, 0.0));

            favoriteGenreMusic.setRate(favoriteGenreMusic.getRate() + 1);

            favoriteGenreMusicRepository.save(favoriteGenreMusic);
        }
    }
}
