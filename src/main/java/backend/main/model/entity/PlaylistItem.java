package backend.main.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PlaylistItem")
@Entity
@Builder
public class PlaylistItem {
    @Id
    private String playlistID;
    @Column
    private String itemID;
}
