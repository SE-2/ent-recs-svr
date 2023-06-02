package backend.main.business.implementation.metadataConvertor;

import backend.main.business.interfaces.metadataConvertor.IMusicToMetadataConvertor;
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
            properties.put("genre", String.valueOf(music.getAlbum()));
            properties.put("singer", String.valueOf(music.getArtist()));
            properties.put("duration", String.valueOf(formatDuration(music.getDuration_ms())));

            String imageUrl = "";
            if(music.getImageUrl() != null) {
                imageUrl = music.getImageUrl();
            }

            MediaMetadata mediaMetadata = new MediaMetadata(
                    music.getId(),
                    "music",
                    music.getTrack(),
                    imageUrl,
                    properties
            );

            mediaMetadataList.add(mediaMetadata);
        }

        return mediaMetadataList;
    }

    private static String formatDuration(float durationInMillis) {
        long seconds = (long) durationInMillis / 1000;
        long minutes = seconds / 60;
        seconds = seconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}

