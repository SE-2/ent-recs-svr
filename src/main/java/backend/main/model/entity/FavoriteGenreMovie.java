package backend.main.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "FavoriteGenreMovie",uniqueConstraints = {@UniqueConstraint(columnNames = {"userID", "genre"})})
@Entity
@Builder
public class FavoriteGenreMovie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String userID;
    @Column
    private String genre;
    @Column
    private double rate;

    public FavoriteGenreMovie(String userID, String genre, double rate) {
        this.userID = userID;
        this.genre = genre;
        this.rate = rate;
    }
}
