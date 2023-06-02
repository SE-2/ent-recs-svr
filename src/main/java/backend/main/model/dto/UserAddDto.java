package backend.main.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAddDto {
    private String email;
    private String birthDate;
}