
package backend.main.repository;

import backend.main.model.entity.Book;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface BookRepository extends ElasticsearchRepository<Book, String> {

    @Query("{\"bool\": {\"must\": [{\"multi_match\": {\"query\": \"?0\", \"type\": \"best_fields\"}}, {\"query_string\": {\"default_field\": \"genre\", \"query\": \"?1\"}}]}}")
    List<Book> searchBooks(String queryString, String genreQuery);

    @Query("{\"bool\": {\"must\": [{\"multi_match\": {\"query\": \"?0\", \"type\": \"best_fields\"}}]}}")
    List<Book> searchBooksWithoutFilter(String query, String filter);
}
