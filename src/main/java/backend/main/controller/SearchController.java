package backend.main.controller;

import backend.main.business.interfaces.service.IMediaSortService;
import backend.main.business.interfaces.service.ISearchMediaService;
import backend.main.model.dto.MediaFilter;
import backend.main.model.dto.MediaType;
import backend.main.model.dto.SearchQuery;
import backend.main.model.entity.MediaMetadata;
import backend.main.model.entity.User;
import backend.main.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
public class SearchController {
    private final Set<ISearchMediaService> searchMediaServices;
    private final IMediaSortService sortService;
    private final UserRepository userRepository;

    @PostMapping("/search")
    public ResponseEntity<List<MediaMetadata>> search(@RequestHeader("Token") String token, @RequestBody SearchQuery searchQuery) {
        User user = userRepository.findByToken(token);
        ISearchMediaService searchService = getSearchService(searchQuery.getFilter().getMediaType());
        List<MediaMetadata> searchResults = searchService.search(user, searchQuery.getQuery(), searchQuery.getFilter());
        List<MediaMetadata> sortResult = sortService.sort(searchResults, searchQuery.getSortMethod());
        return ResponseEntity.ok(sortResult);
    }

    private ISearchMediaService getSearchService(MediaType mediaType) {
        return searchMediaServices.stream()
                .filter(service -> service.type() == mediaType)
                .findFirst()
                .orElseThrow(NotImplementedException::new);
    }
}
