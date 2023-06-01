package backend.main.Interfaces;

import backend.main.model.entity.Podcast;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CSVPodcastDeserializer implements IPodcastDeserializer {
    @Override
    public List<Podcast> deserialize(List<String[]> lines) {
        List<Podcast> podcasts = new ArrayList<>();

        for (String[] line : lines) {
            if (checkValidation(line))
                continue;

            Podcast podcast = createPodcastFromLine(line);
            podcasts.add(podcast);
        }

        return podcasts;
    }

    private Podcast createPodcastFromLine(String[] line) {
        Podcast podcast = new Podcast();
        podcast.setTitle(line[0]);
        podcast.setProducer(line[1]);
        podcast.setGenre(line[2]);
        podcast.setDescription(line[3]);
        podcast.setNum_episodes(Integer.parseInt(line[4]));
        podcast.setRating(Float.parseFloat(line[5]));
        podcast.setNum_reviews(Integer.parseInt(line[6]));
        podcast.setLink(line[7]);
        podcast.setId(line[8]);

        return podcast;
    }

    private boolean checkValidation(String[] line) {
        for (String value : line) {
            if (value.equals("NA")) {
                return true;
            }
        }
        return false;
    }
}
