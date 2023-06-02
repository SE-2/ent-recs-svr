package backend.main.controller;

import backend.main.business.interfaces.service.IBookService;
import backend.main.business.interfaces.service.IUserVisitedItemService;
import backend.main.model.entity.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class BookController {
    private final IBookService bookService;
    private final IUserVisitedItemService userVisitedItemService;

    @PostMapping("/books")
    public ResponseEntity<String> importData(@RequestParam("book") MultipartFile file) {
        if (file.isEmpty())
            return ResponseEntity.badRequest().body("No file selected");
        bookService.importDataFromCSV(file);
        return ResponseEntity.status(HttpStatus.CREATED).body("Data imported successfully");
    }

    @GetMapping("/books/{bookId}")
    public ResponseEntity<Book> getBook(@RequestHeader("Token")String token, @PathVariable String bookId) {
        Optional<Book> optionalBook = bookService.findBook(bookId);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            userVisitedItemService.saveItemVisited(book.getId(), token);
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}