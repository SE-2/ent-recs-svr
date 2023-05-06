package backend.main.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Feedback")
@Entity
@Builder
public class Feedback {
    @Id
    private String userID;
    @Column
    private String type;
    @Column
    private String description;
    @Column
    private boolean checked;
    @Column
    private int priority;
}
