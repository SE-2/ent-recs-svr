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
    private String userID;
    @Column
    private double grade;
    @Column
    private String itemID;
    @Column
    private String date;
}
