package backend.main.model.dto;

import backend.main.model.entity.MediaMetadata;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MediaMetadataDetails {
    MediaMetadata metadata;
    String decoration;
    boolean isBookMarked;
    boolean isLiked;
}
