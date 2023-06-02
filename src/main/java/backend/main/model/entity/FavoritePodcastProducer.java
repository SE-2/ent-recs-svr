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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String userID;
    @Column
    private String podcastProducer;
    @Column
    private double rate;

    public FavoritePodcastProducer(String userID, String podcastProducer, double rate) {
        this.userID = userID;
        this.podcastProducer = podcastProducer;
        this.rate = rate;
    }
}
