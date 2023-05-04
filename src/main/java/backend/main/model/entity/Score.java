package backend.main.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Score")
@Entity
@Builder
public class Score {
    @Id
    private String UserID;
    @Column
    private double Grade;
    @Column
    private String ItemID;
    @Column
    private String Date;
}
