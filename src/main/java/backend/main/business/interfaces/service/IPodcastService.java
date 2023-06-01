package backend.main.business.interfaces.service;

import org.springframework.web.multipart.MultipartFile;

public interface IPodcastService {

    void importDataFromCSV(MultipartFile file);
}
