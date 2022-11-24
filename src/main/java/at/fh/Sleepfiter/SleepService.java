package at.fh.Sleepfiter;

import at.fh.Sleepfiter.entities.Sleep;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class SleepService {
    private String file = "D://fitbit_export.csv";

    public List<Sleep> readSleepRecords() {
        List<Sleep> sleepRecords = new ArrayList<>();
        Path pathToFile = Paths.get(file);

        try (BufferedReader br = Files.newBufferedReader(pathToFile)) {
            String line = br.readLine();

            var readSleep = false;
            while (line != null) {
                String[] attributes = line.split(",");

                if (readSleep && attributes[0].equals("")) {
                    readSleep = false;
                }

                if (readSleep && !attributes[0].equals("Startzeit")) {
                    Sleep record = createSleepRecord(attributes);
                    sleepRecords.add(record);
                }

                if (attributes[0].equals("Schlaf")) {
                    readSleep = true;
                }

                line = br.readLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return sleepRecords;
    }


    private static Sleep createSleepRecord(String[] metadata) {
        Sleep sleep = new Sleep();
        sleep.setStartTime(metadata[0].replace("\"", ""));
        sleep.setEndTime(metadata[1].replace("\"", ""));
        sleep.setMinutesSlept(Integer.parseInt(metadata[2].replace("\"", "")));
        sleep.setMinutesWakedUp(Integer.parseInt(metadata[3].replace("\"", "")));
        sleep.setStandUp(Integer.parseInt(metadata[4].replace("\"", "")));
        sleep.setTimeInBed(Integer.parseInt(metadata[5].replace("\"", "")));
        sleep.setTimeRem(Integer.parseInt(metadata[6].replace("\"", "")));
        sleep.setDeepSleep(Integer.parseInt(metadata[7].replace("\"", "")));
        sleep.setLightSleep(Integer.parseInt(metadata[8].replace("\"", "")));
        return sleep;
    }

}
