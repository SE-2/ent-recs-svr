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
    private String PlaylistID;
    @Column
    private String ItemID;
}
