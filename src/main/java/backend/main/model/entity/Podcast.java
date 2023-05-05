package backend.main.model.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "podcast")
public class Podcast {
    @Id
    private String id;
    @Field(type = FieldType.Text, name = "title")
    private String title;
    @Field(type = FieldType.Text, name = "producer")
    private String producer;
    @Field(type = FieldType.Text, name = "genre")
    private String genre;
    @Field(type = FieldType.Text, name = "description")
    private String description;
    @Field(type = FieldType.Integer, name = "num_episodes")
    private int num_episodes;
    @Field(type = FieldType.Float, name = "rating")
    private float rating;
    @Field(type = FieldType.Integer, name = "num_reviews")
    private int num_reviews;
    @Field(type = FieldType.Text, name = "link")
    private String link;
}
