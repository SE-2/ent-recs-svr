package backend.main.Interfaces;

import backend.main.model.entity.Book;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CSVBookDeserializer implements IBookDeserializer {
    @Override
    public List<Book> deserialize(List<String[]> lines) {
        List<Book> books = new ArrayList<>();

        for (String[] line : lines) {
            if (checkValidation(line))
                continue;

            Book book = createBookFromLine(line);
            books.add(book);
        }

        return books;
    }

    private Book createBookFromLine(String[] line) {
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

        return book;
    }

    private boolean checkValidation(String[] value) {
        for (String a : value)
            if (a.equals(""))
                return true;
        return false;
    }
}
