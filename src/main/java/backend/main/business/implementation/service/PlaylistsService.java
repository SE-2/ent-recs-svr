package backend.main.business.implementation.service;

import backend.main.business.implementation.metadataConvertor.BookToMediaMetadataConverter;
import backend.main.business.implementation.metadataConvertor.MovieToMediaMetadataConverter;
import backend.main.business.implementation.metadataConvertor.MusicToMediaMetadataConverter;
import backend.main.business.implementation.metadataConvertor.PodcastToMediaMetadataConverter;
import backend.main.business.interfaces.service.IPlaylistsService;
import backend.main.model.entity.*;
import backend.main.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PlaylistsService implements IPlaylistsService {
    private final PlaylistsRepository playlistsRepository;
    private final PlaylistItemRepository playlistItemRepository;
    private final BookToMediaMetadataConverter bookToMediaMetadataConverter;
    private final MovieToMediaMetadataConverter movieToMediaMetadataConverter;
    private final MusicToMediaMetadataConverter musicToMediaMetadataConverter;
    private final PodcastToMediaMetadataConverter podcastToMediaMetadataConverter;
    private final MovieRepository movieRepository;
    private final BookRepository bookRepository;
    private final PodcastRepository podcastRepository;
    private final MusicRepository musicRepository;

    @Override
    public void createPlaylist(User user, String name, String types) {
        Playlists playlist = new Playlists(user.getId(), name, LocalDateTime.now().toString(), "",types);
        playlistsRepository.save(playlist);
    }

    @Override
    public void editPlaylist(User user, String name, String types, String playlistID) {
        Optional<Playlists> playlist = playlistsRepository.findByPlaylistIDAndUserID(Integer.valueOf(playlistID),user.getId());
        if (playlist.isPresent()) {
            Playlists pl = playlist.get();
            pl.setName(name);
            pl.setTypes(types);
        } else throw new RuntimeException("Playlist Not fount to edit");
    }

    @Override
    public void deletePlaylist(Integer playlistID, User user) {
        Optional<Playlists> playlist = playlistsRepository.findByPlaylistIDAndUserID(playlistID, user.getId());
        if (playlist.isEmpty()) {
            throw new RuntimeException("PlayList Not Found");
        }
        playlistsRepository.delete(playlist.get());
    }

    public List<Playlists> getAllPlaylistsByUserId(String userId) {
        return playlistsRepository.findByUserID(userId);
    }

    public List<MediaMetadata> getPlaylistItems(String playlistId) {
        List<PlaylistItem> playlistItems = playlistItemRepository.findByPlaylistID(playlistId);

        List<Book> books = new ArrayList<>();
        List<Movie> movies = new ArrayList<>();
        List<Music> musics = new ArrayList<>();
        List<Podcast> podcasts = new ArrayList<>();

        for (PlaylistItem x: playlistItems) {
            if (x.getItemID().startsWith("S")) {
                Optional<Music> oMusic = musicRepository.findById(x.getItemID());
                oMusic.ifPresent(musics::add);
            } else if (x.getItemID().startsWith("M")) {
                Optional<Movie> oMovie = movieRepository.findById(x.getItemID());
                oMovie.ifPresent(movies::add);
            } else if (x.getItemID().startsWith("B")) {
                Optional<Book> oBook = bookRepository.findById(x.getItemID());
                oBook.ifPresent(books::add);
            } else if (x.getItemID().startsWith("P")) {
                Optional<Podcast> oPodcast = podcastRepository.findById(x.getItemID());
                oPodcast.ifPresent(podcasts::add);
            }
        }

        List<MediaMetadata> res = new ArrayList<>();

        List<MediaMetadata> bookMeta = bookToMediaMetadataConverter.convertToMediaMetadata(books);
        List<MediaMetadata> movieMeta = movieToMediaMetadataConverter.convertToMediaMetadata(movies);
        List<MediaMetadata> podcastMeta = podcastToMediaMetadataConverter.convertToMediaMetadata(podcasts);
        List<MediaMetadata> musicMeta = musicToMediaMetadataConverter.convertToMediaMetadata(musics);

        res.addAll(bookMeta);
        res.addAll(movieMeta);
        res.addAll(podcastMeta);
        res.addAll(musicMeta);

        return res;
    }
}
