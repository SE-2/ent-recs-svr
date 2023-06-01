package backend.main.Interfaces;

import com.opencsv.exceptions.CsvValidationException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IFileParser {
    List<String[]> parse(MultipartFile file) throws IOException, CsvValidationException;
}
