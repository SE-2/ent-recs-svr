package backend.main.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "FavoriteActor")
@Entity
@Builder
public class FavoriteActor {
    @Id
    private String userID;
    @Column
    private String name;
    @Column
    private double rate;
}
