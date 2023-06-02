package backend.main.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "FavoriteSinger")
@Entity
@Builder
public class FavoriteSinger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String userID;
    @Column
    private String singer;
    @Column
    private double rate;

    public FavoriteSinger(String userID, String singer, double rate) {
        this.userID = userID;
        this.singer = singer;
        this.rate = rate;
    }
}
