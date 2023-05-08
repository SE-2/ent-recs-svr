package backend.main.service.implementation;

import backend.main.model.entity.Movie;
import backend.main.repository.MovieRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository podcastRepository) {
        this.movieRepository = podcastRepository;
    }

    public void importDataFromCSV(MultipartFile file) {
        try (CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            String[] line;
            List<Movie> dataModels = new ArrayList<>();
            reader.skip(1);
            while ((line = reader.readNext()) != null) {

                Movie movie = new Movie();
                if (checkValidation(line))
                    continue;

                movie.setId(line[0]);
                movie.setPosterLink(line[1]);
                movie.setSeriesTitle(line[2]);
                movie.setReleasedYear(Integer.parseInt(line[3]));
                movie.setCertificate(line[4]);
                movie.setRuntime(line[5]);
                movie.setGenre(line[6]);
                movie.setImdbRating(Float.parseFloat(line[7]));
                movie.setOverview(line[8]);
                movie.setMetaScore(Integer.parseInt(line[9]));
                movie.setDirector(line[10]);
                movie.setStar1(line[11]);
                movie.setStar2(line[12]);
                movie.setStar3(line[13]);
                movie.setStar4(line[14]);
                movie.setNumberOfVotes(Integer.parseInt(line[15]));
                movie.setGross(line[16]);
                dataModels.add(movie);
            }

            movieRepository.saveAll(dataModels);
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }

    private boolean checkValidation(String[] value) {
        for (String a : value)
            if (a.equals(""))
                return true;
        return false;
    }
}
