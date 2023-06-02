package backend.main.repository;

import backend.main.model.entity.Book;
import backend.main.model.entity.Podcast;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;


public interface PodcastRepository extends ElasticsearchRepository<Podcast, String> {


    @Query("{\"bool\": {\"must\": [{\"multi_match\": {\"query\": \"?0\", \"type\": \"best_fields\"}}, {\"query_string\": {\"default_field\": \"genre\", \"query\": \"?1\"}}]}}")
    List<Podcast> searchPodcasts(String query, String filter);

    @Query("{\"bool\": {\"must\": [{\"multi_match\": {\"query\": \"?0\", \"type\": \"best_fields\"}}]}}")
    List<Podcast> searchPodcastsWithoutFilter(String query, String filter);

    List<Podcast> findAllById(List<String>Ids);

}
