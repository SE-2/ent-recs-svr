package backend.main.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "FavoriteActor",uniqueConstraints = {@UniqueConstraint(columnNames = {"userID", "actor"})})
@Entity
@Builder
public class FavoriteActor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String userID;
    @Column
    private String actor;
    @Column
    private double rate;

    public FavoriteActor(String userID, String actor, double rate) {
        this.userID = userID;
        this.actor = actor;
        this.rate = rate;
    }
}
