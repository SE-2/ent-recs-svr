package backend.main.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "FavoriteGenreBook")
@Entity
@Builder
public class FavoriteGenreBook {
    @Id
    private String UserID;
    @Column
    private String Genre;
    @Column
    private double Rate;
}
