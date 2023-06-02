package backend.main.business.implementation.service.search;

import backend.main.business.interfaces.metadataConvertor.IBookToMetadataConvertor;
import backend.main.business.interfaces.service.ISearchMediaService;
import backend.main.business.interfaces.updater.IFavoriteBookUpdater;
import backend.main.model.dto.MediaFilter;
import backend.main.model.dto.MediaType;
import backend.main.model.entity.MediaMetadata;
import backend.main.model.entity.Book;
import backend.main.model.entity.User;
import backend.main.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookSearchService implements ISearchMediaService {
    private final BookRepository bookRepository;
    private final IBookToMetadataConvertor bookConvertor;
    private final IFavoriteBookUpdater favoriteBookUpdater;

    @Override
    public List<MediaMetadata> search(User user, String query, MediaFilter mediaFilter) {
        String filter = join(mediaFilter.getCategories());

        List<Book> books;
        if (!filter.equals(""))
            books = bookRepository.searchBooks(query, filter);
        else
            books = bookRepository.searchBooksWithoutFilter(query, filter);
        favoriteBookUpdater.updateBookFavorites(books, user);
        return bookConvertor.convertToMediaMetadata(books);
    }

    @Override
    public MediaType type() {
        return MediaType.BOOK;
    }

    public String join(List<String> categories) {
        if (!categories.isEmpty())
            return String.join(" OR ", categories);
        else return "";
    }
}
