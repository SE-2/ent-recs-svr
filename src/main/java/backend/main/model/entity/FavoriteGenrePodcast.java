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
    private String userID;
    @Column
    private String genre;
    @Column
    private double rate;
}
