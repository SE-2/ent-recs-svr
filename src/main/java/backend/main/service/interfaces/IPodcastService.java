package backend.main.service.interfaces;

import org.springframework.web.multipart.MultipartFile;

public interface IPodcastService {

    void importDataFromCSV(MultipartFile file);
}
