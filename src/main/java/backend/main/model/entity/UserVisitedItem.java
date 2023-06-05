package backend.main.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "UserVisitedItem",uniqueConstraints = {@UniqueConstraint(columnNames = {"userID", "mediaID", "date", "time"})})
@Entity
@Builder
public class UserVisitedItem {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String userID;
    @Column
    private String itemID;
    @Column
    private String date;
    @Column
    private String time;
}
