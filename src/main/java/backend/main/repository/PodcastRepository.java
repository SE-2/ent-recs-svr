package backend.main.repository;

import backend.main.model.entity.Podcast;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


public interface PodcastRepository extends ElasticsearchRepository<Podcast, String> {
}
