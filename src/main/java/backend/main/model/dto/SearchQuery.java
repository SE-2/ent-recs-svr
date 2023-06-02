package backend.main.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SearchQuery {
    private String query;
    private MediaFilter filter;
    private SortMethod sortMethod;
}
