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
    private String UserID;
    @Column
    private String Type;
    @Column
    private String Description;
    @Column
    private boolean Checked;
    @Column
    private int Priority;
}
