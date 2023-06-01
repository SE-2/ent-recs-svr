package backend.main.business.interfaces.service;

import org.springframework.web.multipart.MultipartFile;

public interface IMusicService {

    void importDataFromCSV(MultipartFile file);
}
