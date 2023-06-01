package backend.main.Interfaces;

import backend.main.model.entity.Music;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CSVMusicDeserializer implements IMusicDeserializer {
    @Override
    public List<Music> deserialize(List<String[]> lines) {
        List<Music> musics = new ArrayList<>();

        for (String[] line : lines) {
            if (checkValidation(line))
                continue;

            Music music = createMusicFromLine(line);
            musics.add(music);
        }

        return musics;
    }

    private Music createMusicFromLine(String[] line) {
        Music music = new Music();
        music.setId(line[0]);
        music.setArtist(line[1]);
        music.setUrl_spotify(line[2]);
        music.setTrack(line[3]);
        music.setAlbum(line[4]);
        music.setAlbum_type(line[5]);
        music.setUri(line[6]);
        music.setDanceability(Float.parseFloat(line[7]));
        music.setEnergy(Float.parseFloat(line[8]));
        music.setKey(Float.parseFloat(line[9]));
        music.setLoudness(Float.parseFloat(line[10]));
        music.setSpeechiness(Float.parseFloat(line[11]));
        music.setAcousticness(Float.parseFloat(line[12]));
        music.setInstrumentalness(Float.parseFloat(line[13]));
        music.setLiveness(Float.parseFloat(line[14]));
        music.setValence(Float.parseFloat(line[15]));
        music.setTempo(Float.parseFloat(line[16]));
        music.setDuration_ms(Float.parseFloat(line[17]));
        music.setUrl_youtube(line[18]);
        music.setTitle(line[19]);
        music.setChannel(line[20]);
        music.setViews(Float.parseFloat(line[21]));
        music.setLikes(Float.parseFloat(line[22]));
        music.setComments(Float.parseFloat(line[23]));
        music.setDescription(line[24]);
        music.setLicensed(Boolean.parseBoolean(line[25]));
        music.setOfficialVideo(Boolean.parseBoolean(line[26]));
        music.setStream(line[27]);

        return music;
    }

    private boolean checkValidation(String[] line) {
        for (String value : line) {
            if (value.equals("")) {
                return true;
            }
        }
        return false;
    }
}
