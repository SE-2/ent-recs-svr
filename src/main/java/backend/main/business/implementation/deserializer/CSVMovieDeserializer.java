package backend.main.business.implementation.deserializer;

import backend.main.model.entity.Movie;
import backend.main.business.interfaces.deserializer.IMovieDeserializer;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CSVMovieDeserializer implements IMovieDeserializer {
    @Override
    public List<Movie> deserialize(List<String[]> lines) {
        List<Movie> movies = new ArrayList<>();

        for (String[] line : lines) {
            if (checkValidation(line))
                continue;

            Movie movie = createMovieFromLine(line);
            movies.add(movie);
        }

        return movies;
    }

    private Movie createMovieFromLine(String[] line) {
        Movie movie = new Movie();
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

        return movie;
    }

    private boolean checkValidation(String[] line) {
        for (String value : line) {
            if (value.equals("")) {
                return true;
            }
        }
        return false;
    }
}
