package backend.main.model.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "movie")
@Builder
public class Movie {

    @Id
    @Field(type = FieldType.Keyword, name = "id")
    private String id;

    @Field(type = FieldType.Text, name = "poster_link")
    private String posterLink;

    @Field(type = FieldType.Text, name = "series_title")
    private String seriesTitle;

    @Field(type = FieldType.Integer, name = "released_year")
    private Integer releasedYear;

    @Field(type = FieldType.Keyword, name = "certificate")
    private String certificate;

    @Field(type = FieldType.Text, name = "runtime")
    private String runtime;

    @Field(type = FieldType.Text, name = "genre")
    private String genre;

    @Field(type = FieldType.Float, name = "imdb_rating")
    private Float imdbRating;

    @Field(type = FieldType.Text, name = "overview")
    private String overview;

    @Field(type = FieldType.Integer, name = "meta_score")
    private Integer metaScore;

    @Field(type = FieldType.Text, name = "director")
    private String director;

    @Field(type = FieldType.Text, name = "star1")
    private String star1;

    @Field(type = FieldType.Text, name = "star2")
    private String star2;

    @Field(type = FieldType.Text, name = "star3")
    private String star3;

    @Field(type = FieldType.Text, name = "star4")
    private String star4;

    @Field(type = FieldType.Integer, name = "no_of_votes")
    private Integer numberOfVotes;

    @Field(type = FieldType.Text, name = "gross")
    private String gross;

}
