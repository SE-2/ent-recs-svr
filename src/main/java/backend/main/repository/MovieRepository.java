
package backend.main.repository;

import backend.main.model.entity.Movie;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface MovieRepository extends ElasticsearchRepository<Movie, String> {

    @Query("{\"bool\": {\"must\": [{\"multi_match\": {\"query\": \"?0\", \"type\": \"best_fields\"}}, {\"query_string\": {\"default_field\": \"genre\", \"query\": \"?1\"}}]}}")
    List<Movie> searchMovies(String queryString, String genreQuery);

    @Query("{\"bool\": {\"must\": [{\"multi_match\": {\"query\": \"?0\", \"type\": \"best_fields\"}}]}}")
    List<Movie> searchMoviesWithoutFilter(String query, String filter);
}