package backend.main.model.entity;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserVisitedItemId implements Serializable {
    private String userID;
    private String itemID;
    private String date;
    private String time;
}
