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
    private String UserID;
    @Column
    private String Name;
    @Column
    private double Rate;
}
