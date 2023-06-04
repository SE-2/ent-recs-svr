package backend.main.business.implementation.service.trend;

import backend.main.business.interfaces.service.ITrendMediaService;
import backend.main.model.dto.MediaType;
import backend.main.model.entity.Like;
import backend.main.model.entity.MediaMetadata;
import backend.main.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TrendMediaService implements ITrendMediaService {
    private final LikeRepository likeRepository;

    @Override
    public List<MediaMetadata> getTodayTrendMedia() {
        List<MediaMetadata> trends = new ArrayList<>();

        Map<String, String> properties = new HashMap<>();
        properties.put("genre", "Leisure");
        properties.put("producer", "Kelly and Marsha");
        trends.add(new MediaMetadata("P115", String.valueOf(MediaType.PODCAST),"Two Ewes Fiber Adventures","https://podcasts.apple.com/us/podcast/two-ewes-fiber-adventures/id928864628",properties));
        Map<String, String> properties2 = new HashMap<>();
        properties2.put("genre", "Fiction");
        properties2.put("producer", "Alma Roda-Gil");
        trends.add(new MediaMetadata("P1332", String.valueOf(MediaType.PODCAST),"The 12:37","https://podcasts.apple.com/us/podcast/the-12-37/id1453598700",properties2));

        Map<String, String> properties3 = new HashMap<>();
        properties3.put("genre", "fiction, fantasy, paranormal, mystery, thriller, crime");
        properties3.put("pages", "224");
        properties3.put("writer", "Isaac Asimov");
        trends.add(new MediaMetadata("B41804", String.valueOf(MediaType.BOOK),"I Robot Robot 01","https://images.gr-assets.com/books/1388321463m/41804.jpg",properties3));
        Map<String, String> properties4 = new HashMap<>();
        properties4.put("genre", "fantasy, paranormal, fiction, comics, graphic, young-adult");
        properties4.put("pages", "228");
        properties4.put("writer", "Terry Pratchett");
        trends.add(new MediaMetadata("B34497", String.valueOf(MediaType.BOOK),"The Color of Magic Discworld 1 Rincewind 1","https://images.gr-assets.com/books/1407111017m/34497.jpg",properties4));

        Map<String, String> properties5 = new HashMap<>();
        properties5.put("productionYear", "2010");
        properties5.put("duration", "134 min");
        properties5.put("genre", "Drama");
        properties5.put("director", "Vikramaditya Motwane");
        trends.add(new MediaMetadata("M141", String.valueOf(MediaType.MOVIE),"Udaan","https://m.media-amazon.com/images/M/MV5BNzgxMzExMzUwNV5BMl5BanBnXkFtZTcwMDc2MjUwNA@@._V1_UY98_CR0,0,67,98_AL_.jpg",properties5));
        Map<String, String> properties6 = new HashMap<>();
        properties6.put("productionYear", "1955");
        properties6.put("duration", "92 min");
        properties6.put("genre", "Crime, Drama, Film-Noir");
        properties6.put("director", "Charles Laughton");
        trends.add(new MediaMetadata("M442", String.valueOf(MediaType.MOVIE),"The Night of the Hunter","https://m.media-amazon.com/images/M/MV5BYTNjN2M2MzYtZGEwMi00Mzc5LWEwYTMtODM1ZmRiZjFiNTU0L2ltYWdlL2ltYWdlXkEyXkFqcGdeQXVyNjc1NTYyMjg@._V1_UX67_CR0,0,67,98_AL_.jpg",properties6));

        Map<String, String> properties7 = new HashMap<>();
        properties7.put("genre", "");
        properties7.put("singer", "Eu nÃ£o sou de me entregar");
        properties7.put("duration", formatDuration(187053));
        trends.add(new MediaMetadata("S17076", String.valueOf(MediaType.MUSIC),"Ferrugem","https://open.spotify.com/artist/5ZfBThYiIIhL7jHMG8gDB2",properties7));
        Map<String, String> properties8 = new HashMap<>();
        properties8.put("genre", "");
        properties8.put("singer", "11:11 Music Group");
        properties8.put("duration", formatDuration(134583));
        trends.add(new MediaMetadata("S11571", String.valueOf(MediaType.MUSIC),"Past Lives","https://open.spotify.com/artist/2MDj296KJIfgWDNBtHzeFi",properties8));
        return trends;
    }

    @Override
    public List<MediaMetadata> getAllTimeTrendMedia() {
        List<MediaMetadata> trends = new ArrayList<>();

        Map<String, String> properties = new HashMap<>();
        properties.put("genre", "Sports");
        properties.put("producer", "KCBS Sports");
        trends.add(new MediaMetadata("P3673", String.valueOf(MediaType.PODCAST),"Fantasy Football Today Podcast","https://podcasts.apple.com/us/podcast/two-ewes-fiber-adventures/id928864628",properties));
        Map<String, String> properties2 = new HashMap<>();
        properties2.put("genre", "Sports");
        properties2.put("producer", "ESPN");
        trends.add(new MediaMetadata("P1949", String.valueOf(MediaType.PODCAST),"The Mina Kimes Show featuring Lenny","https://podcasts.apple.com/us/podcast/the-mina-kimes-show-featuring-lenny/id1437647915",properties2));

        Map<String, String> properties3 = new HashMap<>();
        properties3.put("genre", "romance, fiction, young-adult, fantasy, paranormal");
        properties3.put("pages", "310");
        properties3.put("writer", "Kristin Cast");
        trends.add(new MediaMetadata("B676924", String.valueOf(MediaType.BOOK),"Betrayed House of Night 2","https://s.gr-assets.com/assets/nophoto/book/111x148-bcc042a9c91a29c1d680899eff700a03.png",properties3));
        Map<String, String> properties4 = new HashMap<>();
        properties4.put("genre", "romance, fiction, young-adult, poetry");
        properties4.put("pages", "317");
        properties4.put("writer", "Colleen Hoover");
        trends.add(new MediaMetadata("B13372690", String.valueOf(MediaType.BOOK),"Slammed Slammed 1","https://images.gr-assets.com/books/1328530463m/13372690.jpg",properties4));

        Map<String, String> properties5 = new HashMap<>();
        properties5.put("productionYear", "1987");
        properties5.put("duration", "84 min");
        properties5.put("genre", "Action, Comedy, Fantasy");
        properties5.put("director", "Sam Raimi");
        trends.add(new MediaMetadata("M678", String.valueOf(MediaType.MOVIE),"Evil Dead II","https://m.media-amazon.com/images/M/MV5BMWY3ODZlOGMtNzJmOS00ZTNjLWI3ZWEtZTJhZTk5NDZjYWRjXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_UX67_CR0,0,67,98_AL_.jpg",properties5));
        Map<String, String> properties6 = new HashMap<>();
        properties6.put("productionYear", "1945");
        properties6.put("duration", "86 min");
        properties6.put("genre", "Drama, Romance");
        properties6.put("director", "David Lean");
        trends.add(new MediaMetadata("M452", String.valueOf(MediaType.MOVIE),"Brief Encounter","https://m.media-amazon.com/images/M/MV5BYWQ0MGNjOTYtMWJlNi00YWMxLWFmMzktYjAyNTVkY2U1NWNhL2ltYWdlL2ltYWdlXkEyXkFqcGdeQXVyNjc1NTYyMjg@._V1_UX67_CR0,0,67,98_AL_.jpg",properties6));

        Map<String, String> properties7 = new HashMap<>();
        properties7.put("genre", "");
        properties7.put("singer", "Young Nudy");
        properties7.put("duration", formatDuration(187053));
        trends.add(new MediaMetadata("S17641", String.valueOf(MediaType.MUSIC),"Child's Play (feat. 21 Savage)","https://open.spotify.com/artist/5yPzzu25VzEk8qrGTLIrE1",properties7));
        Map<String, String> properties8 = new HashMap<>();
        properties8.put("genre", "");
        properties8.put("singer", "Luvli");
        properties8.put("duration", formatDuration(213107));
        trends.add(new MediaMetadata("S12421", String.valueOf(MediaType.MUSIC),"My Thoughts","https://open.spotify.com/artist/6dqohi36avY0M9urnyhITr",properties8));
        return trends;
    }
    private static String formatDuration(float durationInMillis) {
        long seconds = (long) durationInMillis / 1000;
        long minutes = seconds / 60;
        seconds = seconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}