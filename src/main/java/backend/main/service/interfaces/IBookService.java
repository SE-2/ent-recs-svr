package backend.main.service.interfaces;

import org.springframework.web.multipart.MultipartFile;

public interface IBookService {

    void importDataFromCSV(MultipartFile file);
}
