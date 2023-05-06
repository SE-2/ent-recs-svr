package backend.main.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "FavoriteWriter")
@Entity
@Builder
public class FavoriteWriter {
    @Id
    private String userID;
    @Column
    private String name;
    @Column
    private double rate;
}
