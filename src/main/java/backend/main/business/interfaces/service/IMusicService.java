package backend.main.business.interfaces.service;

import backend.main.model.entity.Music;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface IMusicService {

    void importDataFromCSV(MultipartFile file);

    Optional<Music> findMusic(String id);
}
