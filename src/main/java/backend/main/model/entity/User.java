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
    @Column(unique = true)
    private String token;
    @Column
    private String birthDate;
    @Column
    private String name;
    @Column
    private String profileImgUrl;
}
