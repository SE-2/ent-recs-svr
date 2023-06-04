package backend.main.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserAddDto {
    private String email;
    private String birthDate;
    private String token;
    private String name;
    private String profileImgUrl;
}