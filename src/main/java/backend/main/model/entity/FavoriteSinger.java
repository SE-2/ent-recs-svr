package backend.main.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "FavoriteSinger")
@Entity
@Builder
public class FavoriteSinger {
    @Id
    private String userID;
    @Column
    private String name;
    @Column
    private double rate;
}
