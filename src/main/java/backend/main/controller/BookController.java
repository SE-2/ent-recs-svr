package backend.main.controller;

import backend.main.business.interfaces.service.IBookService;
import backend.main.model.entity.Book;
import backend.main.model.entity.MediaMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController {
    private final IBookService bookService;

    @PostMapping("/books")
    public ResponseEntity<String> importData(@RequestParam("book") MultipartFile file) {
        if (file.isEmpty())
            return ResponseEntity.badRequest().body("No file selected");
        bookService.importDataFromCSV(file);
        return ResponseEntity.status(HttpStatus.CREATED).body("Data imported successfully");
    }

    @GetMapping("/books/{bookId}")
    public ResponseEntity<Book> getBook(@PathVariable String bookId) {
        return ResponseEntity.ok(new Book());
    }

}