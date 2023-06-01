package backend.main.business.implementation.service;


import backend.main.business.interfaces.parser.IFileParser;
import backend.main.business.interfaces.deserializer.IMusicDeserializer;
import backend.main.model.entity.Music;
import backend.main.repository.MusicRepository;
import com.opencsv.exceptions.CsvValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

@RequiredArgsConstructor
@Service
public class MusicService {
    private final IFileParser fileParser;
    private final IMusicDeserializer musicDeserializer;
    private final MusicRepository musicRepository;

    public void importDataFromCSV(MultipartFile file) {
        try {
            List<String[]> lines = fileParser.parse(file);
            List<Music> musics = musicDeserializer.deserialize(lines);
            musicRepository.saveAll(musics);
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }
}
