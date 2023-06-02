package backend.main.business.interfaces.service;

import backend.main.model.entity.Book;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface IBookService {

    void importDataFromCSV(MultipartFile file);

    Optional<Book> findBook(String id);
}
