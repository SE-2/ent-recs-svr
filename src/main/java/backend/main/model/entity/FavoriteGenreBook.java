package backend.main.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "FavoriteGenreBook",uniqueConstraints = {@UniqueConstraint(columnNames = {"userID", "genre"})})
@Entity
@Builder
public class FavoriteGenreBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String userID;
    @Column
    private String genre;
    @Column
    private double rate;

    public FavoriteGenreBook(String userID, String genre, double rate) {
        this.userID = userID;
        this.genre = genre;
        this.rate = rate;
    }
}
