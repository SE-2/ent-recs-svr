package backend.main.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "FavoriteWriter")
@Entity
@Builder
public class FavoriteWriter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String userID;
    @Column
    private String writer;
    @Column
    private double rate;

    public FavoriteWriter(String userID, String writer, double rate) {
        this.userID = userID;
        this.writer = writer;
        this.rate = rate;
    }
}
