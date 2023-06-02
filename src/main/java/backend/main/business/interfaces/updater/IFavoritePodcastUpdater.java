package backend.main.business.interfaces.updater;

import backend.main.model.entity.*;

import java.util.List;

public interface IFavoritePodcastUpdater {
    void updatePodcastFavorites(List<Podcast> podcasts, User user);
}