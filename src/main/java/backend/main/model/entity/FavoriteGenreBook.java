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
    private String userID;
    @Column
    private String genre;
    @Column
    private double rate;
}
