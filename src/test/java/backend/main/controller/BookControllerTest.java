//package backend.main.controller;
//
//import backend.main.business.interfaces.service.*;
//import backend.main.model.entity.Book;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.mock.web.MockMultipartFile;
//
//import java.io.IOException;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class BookControllerTest {
//
//    @Mock
//    private IBookService bookService;
//
//    @Mock
//    private IUserVisitedItemService userVisitedItemService;
//
//    private BookController bookController;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        bookController = new BookController(bookService, userVisitedItemService);
//    }
//
//    @Test
//    void importData_ValidFile_ReturnsCreatedResponse() {
//        MockMultipartFile file = new MockMultipartFile("book", "data.csv", "text/csv", "file content".getBytes());
//        when(bookService.importDataFromCSV(file)).thenReturn(5);
//
//        ResponseEntity<String> response = bookController.importData(file);
//
//        assertEquals(HttpStatus.CREATED, response.getStatusCode());
//        assertEquals("Data imported successfully. 5", response.getBody());
//        verify(bookService, times(1)).importDataFromCSV(file);
//    }
//
//    @Test
//    void importData_EmptyFile_ReturnsBadRequestResponse() {
//        MockMultipartFile file = new MockMultipartFile("book", "empty.csv", "text/csv", new byte[0]);
//
//        ResponseEntity<String> response = bookController.importData(file);
//
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//        assertEquals("No file selected", response.getBody());
//        verifyNoMoreInteractions(bookService);
//    }
//
//    @Test
//    void getBook_ExistingBook_ReturnsBookAndSavesVisitedItem() {
//        String token = "testToken";
//        String bookId = "123";
//        Book book;
//        book = Book.builder().Id(bookId).title("Book Title").name("Author").build();
//
//        when(bookService.findBook(bookId)).thenReturn(Optional.of(book));
//
//        ResponseEntity<Book> response = bookController.getBook(token, bookId);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(book, response.getBody());
//        verify(userVisitedItemService, times(1)).saveItemVisited(bookId, token);
//    }
//
//    @Test
//    void getBook_NonExistingBook_ReturnsNotFoundResponse() {
//        String token = "testToken";
//        String bookId = "123";
//
//        when(bookService.findBook(bookId)).thenReturn(Optional.empty());
//
//        ResponseEntity<Book> response = bookController.getBook(token, bookId);
//
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//        assertNull(response.getBody());
//        verifyNoMoreInteractions(userVisitedItemService);
//    }
//}