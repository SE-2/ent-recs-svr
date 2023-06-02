package backend.main.business.implementation.metadataConvertor;

import backend.main.business.interfaces.metadataConvertor.IPodcastToMetadataConvertor;
import backend.main.model.entity.*;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PodcastToMediaMetadataConverter implements IPodcastToMetadataConvertor {
    @Override
    public List<MediaMetadata> convertToMediaMetadata(List<Podcast> podcastList) {
        List<MediaMetadata> mediaMetadataList = new ArrayList<>();

        for (Podcast podcast : podcastList) {
            Map<String, String> properties = new HashMap<>();
            properties.put("genre", podcast.getGenre());
            properties.put("producer", podcast.getProducer());

            MediaMetadata mediaMetadata = new MediaMetadata(
                    podcast.getId(),
                    "podcast",
                    podcast.getTitle(),
                    "", // Set the image URL to null for podcasts
                    properties
            );

            mediaMetadataList.add(mediaMetadata);
        }

        return mediaMetadataList;
    }
}
