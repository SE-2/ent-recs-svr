package backend.main.business.interfaces.updater;

import backend.main.model.entity.*;

import java.util.List;

public interface IFavoriteBookUpdater {
    void updateBookFavorites(List<Book> books, User user);
    void updateBookGenres(List<String> genres, User user);
}
