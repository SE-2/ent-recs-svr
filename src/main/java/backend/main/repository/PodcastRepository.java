package backend.main.repository;

import backend.main.model.entity.Podcast;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface PodcastRepository extends ElasticsearchRepository<Podcast, String> {
    List<Podcast> findByName(String name);
}
