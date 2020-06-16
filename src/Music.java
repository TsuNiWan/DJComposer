public class Music {
    private String fileName;
    private int totalTrackNum;
    private int madi;
    private int bpm;
    private String note;
    private String drumNote;

    public Music(String fileName, int totalTrackNum, int madi, int bpm, String note, String drumNote) {
        this.fileName = fileName;
        this.totalTrackNum = totalTrackNum;
        this.madi = madi;
        this.bpm = bpm;
        this.note = note;
        this.drumNote = drumNote;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setTotalTrackNum(int totalTrackNum) {
        this.totalTrackNum = totalTrackNum;
    }

    public int getTotalTrackNum() {
        return totalTrackNum;
    }

    public void setMadi(int madi) {
        this.madi = madi;
    }

    public int getMadi() {
        return madi;
    }

    public void setBpm(int bpm) {
        this.bpm = bpm;
    }

    public int getBpm() {
        return bpm;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNote() {
        return note;
    }

    public void setDrumNote(String drumNote) {
        this.drumNote = drumNote;
    }

    public String getDrumNote() {
        return drumNote;
    }
}