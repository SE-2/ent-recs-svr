package backend.main.model.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "music")
@Builder
public class Music {
    @Id
    @Field(type = FieldType.Keyword, name = "id")
    private String id;

    @Field(type = FieldType.Text, name = "artist")
    private String artist;

    @Field(type = FieldType.Text, name = "url_spotify")
    private String url_spotify;

    @Field(type = FieldType.Text, name = "track")
    private String track;

    @Field(type = FieldType.Text, name = "album")
    private String album;

    @Field(type = FieldType.Text, name = "album_type")
    private String album_type;

    @Field(type = FieldType.Text, name = "uri")
    private String uri;

    @Field(type = FieldType.Float, name = "danceability")
    private float danceability;

    @Field(type = FieldType.Float, name = "energy")
    private float energy;

    @Field(type = FieldType.Float, name = "key")
    private float key;

    @Field(type = FieldType.Float, name = "loudness")
    private float loudness;

    @Field(type = FieldType.Float, name = "speechiness")
    private float speechiness;

    @Field(type = FieldType.Float, name = "acousticness")
    private float acousticness;

    @Field(type = FieldType.Float, name = "instrumentalness")
    private float instrumentalness;

    @Field(type = FieldType.Float, name = "liveness")
    private float liveness;

    @Field(type = FieldType.Float, name = "valence")
    private float valence;

    @Field(type = FieldType.Float, name = "tempo")
    private float tempo;

    @Field(type = FieldType.Float, name = "Duration_ms")
    private float duration_ms;

    @Field(type = FieldType.Text, name = "url_youtube")
    private String url_youtube;

    @Field(type = FieldType.Text, name = "title")
    private String title;

    @Field(type = FieldType.Text, name = "channel")
    private String channel;

    @Field(type = FieldType.Float, name = "views")
    private float views;

    @Field(type = FieldType.Float, name = "likes")
    private float likes;

    @Field(type = FieldType.Float, name = "comments")
    private float comments;

    @Field(type = FieldType.Text, name = "description")
    private String description;

    @Field(type = FieldType.Boolean, name = "licensed")
    private boolean licensed;

    @Field(type = FieldType.Boolean, name = "official_video")
    private boolean officialVideo;

    @Field(type = FieldType.Text, name = "stream")
    private String stream;

    @Field(type = FieldType.Text, name = "image_url")
    private String imageUrl;
}
