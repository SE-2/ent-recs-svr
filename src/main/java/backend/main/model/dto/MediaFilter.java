package backend.main.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MediaFilter {
    private MediaType mediaType;
    private List<String> categories;
}
