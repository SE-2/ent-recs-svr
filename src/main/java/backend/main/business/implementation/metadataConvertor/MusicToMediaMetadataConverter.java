package backend.main.business.implementation.metadataConvertor;

import backend.main.business.interfaces.metadataConvertor.IBookToMetadataConvertor;
import backend.main.business.interfaces.metadataConvertor.IMusicToMetadataConvertor;
import backend.main.model.dto.SearchQuery;
import backend.main.model.entity.Book;
import backend.main.model.entity.MediaMetadata;
import backend.main.model.entity.Music;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Component
public class MusicToMediaMetadataConverter implements IMusicToMetadataConvertor {


    @Override
    public List<MediaMetadata> convertToMediaMetadata(List<Music> musicList) {
        List<MediaMetadata> mediaMetadataList = new ArrayList<>();

        for (Music music : musicList) {
            Map<String, String> properties = new HashMap<>();
            properties.put("danceability", String.valueOf(music.getDanceability()));
            properties.put("genre", String.valueOf(music.getAlbum()));
            properties.put("singer", String.valueOf(music.getArtist()));
            properties.put("duration", String.valueOf(music.getDuration_ms()));

            MediaMetadata mediaMetadata = new MediaMetadata(
                    music.getId(),
                    "music",
                    music.getTitle(),
                    music.getImageUrl(),
                    properties
            );

            mediaMetadataList.add(mediaMetadata);
        }

        return mediaMetadataList;
    }
}

