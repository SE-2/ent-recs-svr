package backend.main.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "User")
@Entity
@Builder
public class User {
    @Id
    private String ID;
    @Column
    private String Email;
    @Column
    private String Token;
    @Column
    private String BirthDate;
}
