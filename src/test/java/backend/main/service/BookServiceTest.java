package backend.main.service;
import backend.main.business.implementation.service.BookService;
import backend.main.business.interfaces.deserializer.IBookDeserializer;
import backend.main.business.interfaces.parser.IFileParser;
import backend.main.model.entity.Book;
import backend.main.repository.BookRepository;
import com.opencsv.exceptions.CsvValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BookServiceTest {
    @Mock
    private IFileParser fileParser;

    @Mock
    private IBookDeserializer bookDeserializer;

    @Mock
    private BookRepository bookRepository;

    private BookService bookService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        bookService = new BookService(fileParser, bookDeserializer, bookRepository);
    }

    @Test
    public void importDataFromCSV_ValidFile_ReturnsSavedCount() throws IOException, CsvValidationException {
        MultipartFile mockFile = mock(MultipartFile.class);
        List<String[]> mockLines = new ArrayList<>();
        List<Book> mockBooks = new ArrayList<>();
        mockLines.add(new String[] { "1", "Book 1" });
        mockLines.add(new String[] { "2", "Book 2" });
        mockBooks.add(Book.builder().Id("1").title("Book 1").build());
        mockBooks.add(Book.builder().Id("2").title("Book 2").build());


        when(fileParser.parse(mockFile)).thenReturn(mockLines);
        when(bookDeserializer.deserialize(mockLines)).thenReturn(mockBooks);
        when(bookRepository.saveAll(mockBooks)).thenReturn(mockBooks);

        int savedCount = bookService.importDataFromCSV(mockFile);

        assertEquals(mockBooks.size(), savedCount);
        verify(fileParser).parse(mockFile);
        verify(bookDeserializer).deserialize(mockLines);
        verify(bookRepository).saveAll(mockBooks);
    }

    @Test
    public void findBook_ExistingId_ReturnsOptionalBook() {
        String mockId = "1";
        Book mockBook = Book.builder().Id(mockId).title("Book 1").build();

        when(bookRepository.findById(mockId)).thenReturn(Optional.of(mockBook));

        Optional<Book> result = bookService.findBook(mockId);

        assertEquals(Optional.of(mockBook), result);
        verify(bookRepository).findById(mockId);
    }

    @Test
    public void findBook_NonExistingId_ReturnsEmptyOptional() {
        String mockId = "1";

        when(bookRepository.findById(mockId)).thenReturn(Optional.empty());

        Optional<Book> result = bookService.findBook(mockId);

        assertEquals(Optional.empty(), result);
        verify(bookRepository).findById(mockId);
    }
}

