package backend.main.business.interfaces.service;

import backend.main.model.entity.Podcast;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface IPodcastService {

    int importDataFromCSV(MultipartFile file);

    Optional<Podcast> findPodcast(String id);

}
