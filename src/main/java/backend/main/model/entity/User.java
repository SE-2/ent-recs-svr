package backend.main.model.entity;

import jakarta.persistence.*;
import lombok.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "User")
@Entity
@Builder
public class User {
    @GeneratedValue(strategy=GenerationType.UUID)
    @Id
    private String id;
    @Column
    private String email;
    @Column
    private String token;
    @Column
    private String birthDate;
}
