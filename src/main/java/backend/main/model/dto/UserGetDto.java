package backend.main.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class UserGetDto {
    private String name;
    private String profileImgUrl;
}
