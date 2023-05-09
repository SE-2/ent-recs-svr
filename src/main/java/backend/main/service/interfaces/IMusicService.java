package backend.main.service.interfaces;

import org.springframework.web.multipart.MultipartFile;

public interface IMusicService {

    void importDataFromCSV(MultipartFile file);
}
