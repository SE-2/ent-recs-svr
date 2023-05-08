package backend.main.service.implementation;


import backend.main.model.entity.Music;
import backend.main.repository.MusicRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

@Service
public class MusicService {

    private final MusicRepository musicRepository;

    @Autowired
    public MusicService(MusicRepository musicRepository) {
        this.musicRepository = musicRepository;
    }

    public void importDataFromCSV(MultipartFile file) {
        try (CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            String[] line;
            List<Music> musics = new ArrayList<>();

            reader.readNext();
            while ((line = reader.readNext()) != null) {
                if (checkValidation(line))
                    continue;
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

                musics.add(music);
            }

            musicRepository.saveAll(musics);
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }
    private boolean checkValidation(String[] value) {
        for (String a : value)
            if (a.equals(""))
                return true;
        return false;
    }

}
