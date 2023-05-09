package backend.main.service.implementation;


import backend.main.model.entity.Book;
import backend.main.repository.BookRepository;
import backend.main.service.interfaces.IBookService;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

@RequiredArgsConstructor
@Service
public class BookService implements IBookService {
    private final BookRepository bookRepository;

    public void importDataFromCSV(MultipartFile file) {
        try (CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            String[] line;
            List<Book> books = new ArrayList<>();

            // Skip the header row
            reader.readNext();

            while ((line = reader.readNext()) != null) {
                if (checkValidation(line))
                    continue;
                Book book = new Book();
                book.setId(line[0]);
                book.setTitle(line[1]);
                book.setImageUrl(line[2]);
                book.setUrl(line[3]);
                book.setNumPages(Integer.parseInt(line[4]));
                book.setRatingsCount(Integer.parseInt(line[5]));
                book.setDescription(line[6]);
                book.setGenre(line[7]);
                book.setName(line[8]);

                books.add(book);
            }

            bookRepository.saveAll(books);
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }
    private boolean checkValidation(String[] value) {
        for (String a : value)
            if (a.equals(""))
                return true;
        return false;
    }
}
