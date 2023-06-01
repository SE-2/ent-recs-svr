package backend.main.business.interfaces.deserializer;

import backend.main.model.entity.Podcast;

import java.util.List;

public interface IPodcastDeserializer {
    List<Podcast> deserialize(List<String[]> lines);
}