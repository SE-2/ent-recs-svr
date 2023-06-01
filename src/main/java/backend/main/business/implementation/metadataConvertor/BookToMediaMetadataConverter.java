package backend.main.business.implementation.metadataConvertor;

import backend.main.business.interfaces.metadataConvertor.IBookToMetadataConvertor;
import backend.main.model.dto.SearchQuery;
import backend.main.model.entity.Book;
import backend.main.model.entity.MediaMetadata;
import backend.main.repository.BookRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.*;

@Component
public class BookToMediaMetadataConverter implements IBookToMetadataConvertor {

    @Override
    public List<MediaMetadata> convertToMediaMetadata(List<Book> books) {
        List<MediaMetadata> mediaMetadataList = new ArrayList<>();

        for (Book book : books) {
            Map<String, String> properties = new HashMap<>();
            properties.put("pages", book.getNumPages().toString());
            properties.put("genre", book.getGenre());
            properties.put("writer", book.getName());


            MediaMetadata mediaMetadata = new MediaMetadata(
                    book.getId(),
                    "book",
                    book.getTitle(),
                    book.getImageUrl(),
                    properties
            );

            mediaMetadataList.add(mediaMetadata);
        }

        return mediaMetadataList;
    }
}
