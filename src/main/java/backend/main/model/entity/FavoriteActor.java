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
    private String UserID;
    @Column
    private String Name;
    @Column
    private double Rate;
}
