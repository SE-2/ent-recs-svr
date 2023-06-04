package backend.main.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PlaylistItem",uniqueConstraints = {@UniqueConstraint(columnNames = {"userID", "itemID"})})
@Entity
@Builder
public class PlaylistItem {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    @Column
    private String userID;
    @Column
    private String playlistID;
    @Column
    private String itemID;

    public PlaylistItem(String userID, String playlistID, String itemID) {
        this.userID = userID;
        this.playlistID = playlistID;
        this.itemID = itemID;
    }
}
