package backend.main.business.interfaces.deserializer;

import backend.main.model.entity.Music;

import java.util.List;

public interface IMusicDeserializer {
    List<Music> deserialize(List<String[]> lines);
}
