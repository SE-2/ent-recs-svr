package backend.main.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "FavoritePodcastProducer")
@Entity
@Builder
public class FavoritePodcastProducer {
    @Id
    private String userID;
    @Column
    private String name;
    @Column
    private double rate;
}
