public class Track {
    private int trackNum;
    private String instrument;
    private String note;

    public Track(int trackNum, String instrument, String note) {
        this.trackNum = trackNum;
        this.instrument = instrument;
        this.note = note;
    }

    public void setTrackNum(int trackNum) {
        this.trackNum = trackNum;
    }

    public int getTrackNum() {
        return trackNum;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNote() {
        return note;
    }

    @Override
    public String toString() {
        return "V" + getTrackNum() + " I[" + getInstrument() + "] " + getNote() + " ";
    }
}