package backend.main.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "FavoriteGenrePodcast")
@Entity
@Builder
public class FavoriteGenrePodcast {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String userID;
    @Column
    private String genre;
    @Column
    private double rate;

    public FavoriteGenrePodcast(String userID, String genre, double rate) {
        this.userID = userID;
        this.genre = genre;
        this.rate = rate;
    }
}
