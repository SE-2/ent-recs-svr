package backend.main.business.implementation.service;

import backend.main.business.interfaces.parser.IFileParser;
import backend.main.business.interfaces.deserializer.IPodcastDeserializer;
import backend.main.business.interfaces.service.IPodcastService;
import backend.main.model.entity.Podcast;
import backend.main.repository.PodcastRepository;
import com.opencsv.exceptions.CsvValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

@RequiredArgsConstructor
@Service
public class PodcastService implements IPodcastService {
    private final IFileParser fileParser;
    private final IPodcastDeserializer podcastDeserializer;
    private final PodcastRepository podcastRepository;

    public int importDataFromCSV(MultipartFile file) {
        int savedCount = 0;
        try {
            List<String[]> lines = fileParser.parse(file);
            List<Podcast> podcasts = podcastDeserializer.deserialize(lines);
            podcastRepository.saveAll(podcasts);
            savedCount = podcasts.size();
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        return savedCount;
    }

    public Optional<Podcast> findPodcast(String id) {
        return podcastRepository.findById(id);
    }
}
