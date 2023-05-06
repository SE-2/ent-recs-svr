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
    private String playlistID;
    @Column
    private String userID;
    @Column
    private String name;
    @Column
    private String creationDate;
    @Column
    private String lastVisitedItemID;
}
