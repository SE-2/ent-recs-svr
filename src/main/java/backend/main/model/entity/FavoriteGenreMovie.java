package backend.main.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "FavoriteGenreMovie")
@Entity
@Builder
public class FavoriteGenreMovie {
    @Id
    private String userID;
    @Column
    private String genre;
    @Column
    private double rate;
}
