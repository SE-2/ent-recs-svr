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
    private String playlistID;
    @Column
    private String itemID;
    @Column
    private String date;
}
