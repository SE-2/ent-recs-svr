package backend.main.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Like")
@Entity
@Builder
public class Like {
    @Id
    private String userID;
    @Column
    private String mediaId;
    @Column
    private String date;
}
