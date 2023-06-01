package backend.main.business.implementation.parser;

import backend.main.business.interfaces.parser.IFileParser;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

@Component
public class CSVFileParser implements IFileParser {
    @Override
    public List<String[]> parse(MultipartFile file) throws IOException, CsvValidationException {
        try (CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            List<String[]> lines = new ArrayList<>();

            // Skip the header row
            reader.readNext();

            String[] line;
            while ((line = reader.readNext()) != null) {
                lines.add(line);
            }

            return lines;
        }
    }
}
