package backend.main.repository;

import backend.main.model.entity.Movie;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


public interface MovieRepository extends ElasticsearchRepository<Movie, String> {

}
