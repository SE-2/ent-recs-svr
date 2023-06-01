package backend.main.Interfaces;

import backend.main.model.entity.Book;

import java.util.List;

public interface IBookDeserializer {
    List<Book> deserialize(List<String[]> lines);
}
