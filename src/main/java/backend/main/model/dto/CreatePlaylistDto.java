package backend.main.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePlaylistDto {
    String name;
    String mediaTypes;
}
