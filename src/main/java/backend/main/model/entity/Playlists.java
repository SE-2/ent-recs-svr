package backend.main.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Playlists")
@Entity
@Builder
public class Playlists {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer playlistID;
    @Column
    private String userID;
    @Column
    private String name;
    @Column
    private String creationDate;
    @Column
    private String lastVisitedItemID;
    @Column
    private String types;

    public Playlists(String userID, String name, String creationDate, String lastVisitedItemID,String types) {
        this.userID = userID;
        this.name = name;
        this.creationDate = creationDate;
        this.lastVisitedItemID = lastVisitedItemID;
        this.types = types;
    }
}
