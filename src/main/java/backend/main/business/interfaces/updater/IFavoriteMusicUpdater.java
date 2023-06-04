package backend.main.business.interfaces.updater;

import backend.main.model.entity.*;

import java.util.List;

public interface IFavoriteMusicUpdater {
    void updateMusicFavorites(List<Music> musics, User user);
    void updateMusicGenres(List<String> genres, User user);
}