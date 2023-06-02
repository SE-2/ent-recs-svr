package backend.main.business.implementation.service.search;

import backend.main.business.interfaces.metadataConvertor.IMusicToMetadataConvertor;
import backend.main.business.interfaces.service.ISearchMediaService;
import backend.main.model.dto.MediaFilter;
import backend.main.model.dto.MediaType;
import backend.main.model.dto.SearchQuery;
import backend.main.model.entity.MediaMetadata;
import backend.main.model.entity.Music;
import backend.main.repository.MusicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MusicSearchService implements ISearchMediaService {
    private final MusicRepository musicRepository;
    private final IMusicToMetadataConvertor musicConvertor;

    @Override
    public List<MediaMetadata> search(String query, MediaFilter mediaFilter) {
        String filter = join(mediaFilter.getCategories());
        List<Music> musics = musicRepository.searchMusics(query, filter);
        return musicConvertor.convertToMediaMetadata(musics);
    }

    @Override
    public MediaType type() {
        return MediaType.MUSIC;
    }

    public String join(List<String> categories) {
        if (!categories.isEmpty())
            return String.join(" OR ", categories);
        else return "";
    }
}
