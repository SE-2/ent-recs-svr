package backend.main.repository;

import backend.main.model.entity.Music;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


public interface MusicRepository extends ElasticsearchRepository<Music, String> {

}
