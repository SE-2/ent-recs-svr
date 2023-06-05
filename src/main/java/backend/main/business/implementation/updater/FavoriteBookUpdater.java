package backend.main.business.implementation.updater;

import backend.main.business.interfaces.updater.IFavoriteBookUpdater;
import backend.main.model.entity.Book;
import backend.main.model.entity.FavoriteGenreBook;
import backend.main.model.entity.FavoriteWriter;
import backend.main.model.entity.User;
import backend.main.repository.FavoriteGenreBookRepository;
import backend.main.repository.FavoriteWriterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Component
public class FavoriteBookUpdater implements IFavoriteBookUpdater {

    private final FavoriteGenreBookRepository favoriteGenreBookRepository;
    private final FavoriteWriterRepository favoriteWriterRepository;

    @Override
    public void updateBookFavorites(List<Book> books, User user) {

        List<String> allGenres = new ArrayList<>();
        List<String> allWriters = new ArrayList<>();
        for (int i = 0; i < Math.min(3, books.size()); i++) {
            Book book = books.get(i);
            String[] genresArray = book.getGenre().split(",\\s*");
            List<String> genres = Arrays.asList(genresArray);
            allGenres.addAll(genres);
            allWriters.add(book.getName());
        }
        for (String genre : allGenres) {
            FavoriteGenreBook favoriteGenreBook = favoriteGenreBookRepository.findByUserIDAndGenre(user.getId(), genre)
                    .orElse(new FavoriteGenreBook(user.getId(), genre, 0.0));

            favoriteGenreBook.setRate(favoriteGenreBook.getRate() + 1);

            favoriteGenreBookRepository.save(favoriteGenreBook);
        }
        for (String writer : allWriters) {
            FavoriteWriter favoriteWriter = favoriteWriterRepository.findByUserIDAndWriter(user.getId(), writer)
                    .orElse(new FavoriteWriter(user.getId(), writer, 0.0));

            favoriteWriter.setRate(favoriteWriter.getRate() + 1);

            favoriteWriterRepository.save(favoriteWriter);
        }
    }

    @Override
    public void updateBookGenres(List<String> genres, User user) {

        for (String genre : genres) {
            FavoriteGenreBook favoriteGenreBook = favoriteGenreBookRepository.findByUserIDAndGenre(user.getId(), genre)
                    .orElse(new FavoriteGenreBook(user.getId(), genre, 0.0));

            favoriteGenreBook.setRate(favoriteGenreBook.getRate() + 1);

            favoriteGenreBookRepository.save(favoriteGenreBook);
        }
    }
}
