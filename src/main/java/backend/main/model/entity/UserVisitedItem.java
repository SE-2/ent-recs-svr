package backend.main.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "UserVisitedItem")
@Entity
@Builder
public class UserVisitedItem {
    @Id
    private String PlaylistID;
    @Column
    private String ItemID;
}
