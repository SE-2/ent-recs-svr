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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String userID;
    @Column
    private String director;
    @Column
    private double rate;

    public FavoriteDirector(String userID, String director, double rate) {
        this.userID = userID;
        this.director = director;
        this.rate = rate;
    }
}
