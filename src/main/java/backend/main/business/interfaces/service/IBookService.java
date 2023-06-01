package backend.main.business.interfaces.service;

import org.springframework.web.multipart.MultipartFile;

public interface IBookService {

    void importDataFromCSV(MultipartFile file);
}
