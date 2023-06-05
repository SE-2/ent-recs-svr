package backend.main.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "UserVisitedItem")
@Entity
@Builder
@IdClass(UserVisitedItemId.class)
public class UserVisitedItem {
    @Id
    private String userID;
    @Id
    @Column(length = 20)
    private String itemID;
    @Id
    @Column(length = 20)
    private String date;
    @Id
    @Column(length = 20)
    private String time;
}