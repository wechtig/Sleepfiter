package at.fh.Sleepfiter.entities;

import java.time.LocalDateTime;

public class Sleep {
    private String startTime;
    private String endTime;
    private int minutesSlept;
    private int minutesWakedUp;
    private int standUp;
    private int timeInBed;
    private int timeRem;
    private int deepSleep;
    private int lightSleep;

    public Sleep(String startTime, String endTime, int minutesSlept, int minutesWakedUp,
                 int standUp, int timeInBed, int timeRem, int deepSleep, int lightSleep) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.minutesSlept = minutesSlept;
        this.minutesWakedUp = minutesWakedUp;
        this.standUp = standUp;
        this.timeInBed = timeInBed;
        this.timeRem = timeRem;
        this.deepSleep = deepSleep;
        this.lightSleep = lightSleep;
    }

    public Sleep() {

    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getMinutesSlept() {
        return minutesSlept;
    }

    public void setMinutesSlept(int minutesSlept) {
        this.minutesSlept = minutesSlept;
    }

    public int getMinutesWakedUp() {
        return minutesWakedUp;
    }

    public void setMinutesWakedUp(int minutesWakedUp) {
        this.minutesWakedUp = minutesWakedUp;
    }

    public int getStandUp() {
        return standUp;
    }

    public void setStandUp(int standUp) {
        this.standUp = standUp;
    }

    public int getTimeInBed() {
        return timeInBed;
    }

    public void setTimeInBed(int timeInBed) {
        this.timeInBed = timeInBed;
    }

    public int getTimeRem() {
        return timeRem;
    }

    public void setTimeRem(int timeRem) {
        this.timeRem = timeRem;
    }

    public int getDeepSleep() {
        return deepSleep;
    }

    public void setDeepSleep(int deepSleep) {
        this.deepSleep = deepSleep;
    }

    public int getLightSleep() {
        return lightSleep;
    }

    public void setLightSleep(int lightSleep) {
        this.lightSleep = lightSleep;
    }
}
