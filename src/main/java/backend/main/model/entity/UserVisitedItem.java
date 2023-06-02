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
    private String userID;
    @Column
    private String itemID;
}
