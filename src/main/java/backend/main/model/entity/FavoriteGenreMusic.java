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
    private String UserID;
    @Column
    private String Genre;
    @Column
    private double Rate;
}
