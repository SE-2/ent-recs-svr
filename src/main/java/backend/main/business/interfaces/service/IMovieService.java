package backend.main.business.interfaces.service;

import org.springframework.web.multipart.MultipartFile;

public interface IMovieService {

    void importDataFromCSV(MultipartFile file);
}
