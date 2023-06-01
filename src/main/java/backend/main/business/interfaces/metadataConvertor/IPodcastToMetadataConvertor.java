package backend.main.business.interfaces.metadataConvertor;

import backend.main.model.entity.MediaMetadata;
import backend.main.model.entity.Podcast;

import java.util.List;

public interface IPodcastToMetadataConvertor {
    List<MediaMetadata> convertToMediaMetadata(List<Podcast> podcasts);
}
