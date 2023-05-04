package backend.main.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "UserBlackList")
@Entity
@Builder
public class UserBlackList {
    @Id
    private String PlaylistID;
    @Column
    private String ItemID;
    @Column
    private String Date;
}
