package backend.main.business.implementation.service;


import backend.main.business.interfaces.deserializer.IBookDeserializer;
import backend.main.business.interfaces.service.IBookService;
import backend.main.model.entity.Book;
import backend.main.repository.BookRepository;
import backend.main.business.interfaces.parser.IFileParser;
import com.opencsv.exceptions.CsvValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

@RequiredArgsConstructor
@Service
public class BookService implements IBookService {
    private final IFileParser fileParser;
    private final IBookDeserializer bookDeserializer;
    private final BookRepository bookRepository;

    public int importDataFromCSV(MultipartFile file) {
        int savedCount = 0;
        try {
            List<String[]> lines = fileParser.parse(file);
            List<Book> books = bookDeserializer.deserialize(lines);
            Iterable<Book> savedBooks = bookRepository.saveAll(books);
            savedCount = books.size();
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        return savedCount;
    }

    @Override
    public Optional<Book> findBook(String id) {
        return bookRepository.findById(id);
    }
}