package backend.main.business.interfaces.metadataConvertor;

import backend.main.model.dto.SearchQuery;
import backend.main.model.entity.Book;
import backend.main.model.entity.MediaMetadata;

import java.util.ArrayList;
import java.util.List;

public interface IBookToMetadataConvertor {

    List<MediaMetadata> convertToMediaMetadata(List<Book> books);
}
