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
    private String UserID;
    @Column
    private String Name;
    @Column
    private double Rate;
}
