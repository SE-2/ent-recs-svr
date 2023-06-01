package backend.main.business.interfaces.metadataConvertor;

import backend.main.model.dto.SearchQuery;
import backend.main.model.entity.Book;
import backend.main.model.entity.MediaMetadata;
import backend.main.model.entity.Music;

import java.util.List;

public interface IMusicToMetadataConvertor {
    List<MediaMetadata> convertToMediaMetadata(List<Music> musics);
}
