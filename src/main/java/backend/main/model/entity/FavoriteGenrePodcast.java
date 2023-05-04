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
    private String UserID;
    @Column
    private String Genre;
    @Column
    private double Rate;
}
