package backend.main.controller;

import backend.main.business.interfaces.metadataConvertor.IBookToMetadataConvertor;
import backend.main.business.interfaces.metadataConvertor.IMovieToMetadataConvertor;
import backend.main.business.interfaces.service.*;
import backend.main.model.dto.MediaMetadataDetails;
import backend.main.model.entity.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class BookController {
    private final IBookService bookService;
    private final IUserVisitedItemService userVisitedItemService;
    private final IBookToMetadataConvertor metadataConvertor;
    private final ILikeService likeService;
    private final IPlaylistItemService playlistItemService;
    private final IUserService userService;

    @PostMapping("/books")
    public ResponseEntity<String> importData(@RequestParam("book") MultipartFile file) {
        if (file.isEmpty())
            return ResponseEntity.badRequest().body("No file selected");
        int savedCount = bookService.importDataFromCSV(file);
        return ResponseEntity.status(HttpStatus.CREATED).body("Data imported successfully. " + savedCount);
    }

    @GetMapping("/books/{bookId}")
    public ResponseEntity<MediaMetadataDetails> getBook(@RequestHeader("Token")String token, @PathVariable String bookId) {
        Optional<Book> optionalBook = bookService.findBook(bookId);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            userVisitedItemService.saveItemVisited(book.getId(), token);
            var metadata = metadataConvertor.convertToMediaMetadata(List.of(book)).get(0);

            var response = MediaMetadataDetails.builder()
                    .metadata(metadata)
                    .decoration(book.getDescription())
                    .isBookMarked(playlistItemService.isBookmarked(bookId, userService.getUser(token).getId()))
                    .isLiked(likeService.isLiked(metadata.getMediaId(), token))
                    .build();

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}