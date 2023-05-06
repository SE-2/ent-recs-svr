package backend.main.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "FavoriteDirector")
@Entity
@Builder
public class FavoriteDirector {
    @Id
    private String userID;
    @Column
    private String name;
    @Column
    private double rate;
}
