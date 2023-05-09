package backend.main.service.interfaces;

import org.springframework.web.multipart.MultipartFile;

public interface IMovieService {

    void importDataFromCSV(MultipartFile file);
}
