package backend.main.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "FavoriteGenreMusic")
@Entity
@Builder
public class FavoriteGenreMusic {
    @Id
    private String userID;
    @Column
    private String genre;
    @Column
    private double rate;
}
