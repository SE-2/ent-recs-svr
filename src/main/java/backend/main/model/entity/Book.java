package backend.main.model.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "book")
@Builder
public class Book {
    @Id
    @Field(type = FieldType.Keyword, name = "id")
    private String Id;

    @Field(type = FieldType.Text, name = "title")
    private String title;

    @Field(type = FieldType.Text, name = "image_url")
    private String imageUrl;

    @Field(type = FieldType.Text, name = "url")
    private String url;

    @Field(type = FieldType.Integer, name = "num_pages")
    private Integer numPages;

    @Field(type = FieldType.Integer, name = "ratings_count")
    private Integer ratingsCount;

    @Field(type = FieldType.Text, name = "description")
    private String description;

    @Field(type = FieldType.Text, name = "genre")
    private String genre;

    @Field(type = FieldType.Text, name = "name")
    private String name;

    @Field(type = FieldType.Text, name = "book_id_mapping")
    private String bookIdMapping;
}
