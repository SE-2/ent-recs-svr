package backend.main.model.entity;


import lombok.*;

import java.util.*;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MediaMetadata {
    private String mediaId;
    private String type;
    private String title;
    private String imageUrl;
    private Map<String, String> properties;
}
