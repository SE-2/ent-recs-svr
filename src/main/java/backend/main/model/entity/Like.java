package backend.main.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "MediaLike",uniqueConstraints = {@UniqueConstraint(columnNames = {"userID", "mediaID"})})
@Entity
@Builder
public class Like {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String userID;
    @Column
    private String mediaId;
    @Column
    private String date;
}
