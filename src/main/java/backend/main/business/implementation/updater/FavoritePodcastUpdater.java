package backend.main.business.implementation.updater;

import backend.main.business.interfaces.updater.IFavoritePodcastUpdater;
import backend.main.model.entity.FavoriteGenrePodcast;
import backend.main.model.entity.FavoritePodcastProducer;
import backend.main.model.entity.Podcast;
import backend.main.model.entity.User;
import backend.main.repository.FavoriteGenrePodcastRepository;
import backend.main.repository.FavoritePodcastProducerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@RequiredArgsConstructor
@Component
public class FavoritePodcastUpdater implements IFavoritePodcastUpdater {
    private final FavoriteGenrePodcastRepository favoriteGenrePodcastRepository;
    private final FavoritePodcastProducerRepository favoritePodcastProducerRepository;

    @Override
    public void updatePodcastFavorites(List<Podcast> podcasts, User user) {
        List<String> allProducers = new ArrayList<>();
        List<String> allGenres = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Podcast podcast = podcasts.get(i);
            allGenres.add(podcast.getGenre());
            allProducers.add(podcast.getProducer());
        }

        for (String genre : allGenres) {
            FavoriteGenrePodcast favoriteGenrePodcast = favoriteGenrePodcastRepository.findByUserIDAndGenre(user.getId(), genre)
                    .orElse(new FavoriteGenrePodcast(user.getId(), genre, 0.0));

            favoriteGenrePodcast.setRate(favoriteGenrePodcast.getRate() + 1);

            favoriteGenrePodcastRepository.save(favoriteGenrePodcast);
        }

        for (String producer : allProducers) {
            FavoritePodcastProducer favoritePodcastProducer = favoritePodcastProducerRepository.findByUserIDAndPodcastProducer(user.getId(), producer)
                    .orElse(new FavoritePodcastProducer(user.getId(), producer, 0.0));

            favoritePodcastProducer.setRate(favoritePodcastProducer.getRate() + 1);

            favoritePodcastProducerRepository.save(favoritePodcastProducer);
        }
    }

    @Override
    public void updatePodcastGenres(List<String> genres, User user) {

        for (String genre : genres) {
            FavoriteGenrePodcast favoriteGenrePodcast = favoriteGenrePodcastRepository.findByUserIDAndGenre(user.getId(), genre)
                    .orElse(new FavoriteGenrePodcast(user.getId(), genre, 0.0));

            favoriteGenrePodcast.setRate(favoriteGenrePodcast.getRate() + 1);

            favoriteGenrePodcastRepository.save(favoriteGenrePodcast);
        }
    }
}
