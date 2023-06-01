
package backend.main.repository;

import backend.main.model.entity.Music;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;


public interface MusicRepository extends ElasticsearchRepository<Music, String> {

    @Query("{\"bool\": {\"must\": [{\"multi_match\": {\"query\": \"?0\", \"type\": \"best_fields\"}}]}}")
    List<Music> searchMusics(String query, String filter);
}