package backend.main.service.implementation;

import backend.main.model.entity.Podcast;
import backend.main.repository.PodcastRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

@Service
public class PodcastService {
    private final PodcastRepository podcastRepository;

    @Autowired
    public PodcastService(PodcastRepository podcastRepository) {
        this.podcastRepository = podcastRepository;
    }

    public void importDataFromCSV(MultipartFile file) {
        try (CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            String[] line;
            List<Podcast> dataModels = new ArrayList<>();
            reader.skip(1);
            while ((line = reader.readNext()) != null) {
                // Split the CSV line into individual values

                // Create a new instance of your data model
                Podcast podcast = new Podcast();
                if (checkValidation(line))
                    continue;
                // Set the values from the CSV to the corresponding fields in your data model
                podcast.setTitle(line[0]);
                podcast.setProducer(line[1]);
                podcast.setGenre(line[2]);
                podcast.setDescription(line[3]);
                podcast.setNum_episodes(Integer.parseInt(line[4]));
                podcast.setRating(Float.parseFloat(line[5]));
                podcast.setNum_reviews(Integer.parseInt(line[6]));
                podcast.setLink(line[7]);
                podcast.setId(line[8]);

                dataModels.add(podcast);
            }

            // Save the data models to Elasticsearch using the repository
            podcastRepository.saveAll(dataModels);
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }

    private boolean checkValidation(String[] value) {
        for (String a : value)
            if (a.equals("NA"))
                return true;
        return false;
    }
}
