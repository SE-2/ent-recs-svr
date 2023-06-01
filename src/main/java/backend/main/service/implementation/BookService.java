package backend.main.service.implementation;


import backend.main.Interfaces.IBookDeserializer;
import backend.main.model.entity.Book;
import backend.main.repository.BookRepository;
import backend.main.Interfaces.IFileParser;
import com.opencsv.exceptions.CsvValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

@RequiredArgsConstructor
@Service
public class BookService {
    private final IFileParser fileParser;
    private final IBookDeserializer bookDeserializer;
    private final BookRepository bookRepository;

    public void importDataFromCSV(MultipartFile file) {
        try {
            List<String[]> lines = fileParser.parse(file);
            List<Book> books = bookDeserializer.deserialize(lines);
            bookRepository.saveAll(books);
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }
}