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
    private String PlaylistID;
    @Column
    private String UserID;
    @Column
    private String Name;
    @Column
    private String CreationDate;
    @Column
    private String LastVisitedItemID;
}
