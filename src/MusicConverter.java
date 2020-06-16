import java.util.ArrayList;

public class MusicConverter {
    private Music music;
    private ArrayList<Track> tracks;
    private ArrayList<String> drumTracks;
    private int bpm;

    public MusicConverter(Music music) {// 將預設音符檔案轉換至arraylist
        this.music = music;
        musicToTrackArray();
        musicToDrumArray();
        bpm = music.getBpm();
    }

    public void musicToTrackArray() {
        tracks = new ArrayList<Track>();
        // music note 假設為"V0 I[Piano] G E E. V1 I[Cello] R R R. "
        String[] track = music.getNote().split("V");// track[0]為"0 I[Piano] G E E. "
        for (int i = 0; i < track.length; i++) {
            if (!track[i].equals("")) {// 第一個track為empty string
                int instStartIndex = track[i].indexOf("[") + 1;
                int instEndIndex = track[i].indexOf("]");// substring會自動-1
                int trackNum = Integer.parseInt(track[i].charAt(0) + "");// trackNote[i]的第一個字為trackNum
                String instrument = track[i].substring(instStartIndex, instEndIndex);// 取得樂器名稱
                String note = track[i].substring(instEndIndex + 1);
                tracks.add(new Track(trackNum, instrument, note));
            }
        }
    }

    public void musicToDrumArray() {
        drumTracks = new ArrayList<String>();
        String[] drumNotes = music.getDrumNote().split("@");
        for (int i = 0; i < drumNotes.length; i++) {
            drumTracks.add(drumNotes[i]);
        }
    }

    public void addTrack() {
        int trackNum = 0;
        trackNum = music.getTotalTrackNum();// 一開始totalTrackNum為1(V0),addTrack為V1

        // add 64 notes
        tracks.add(new Track(trackNum, "Piano",
                "Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri "));
        saveTrackToMusic();
    }

    public void removeTrack(int trackNum) {
        for (int i = 0; i < tracks.size(); i++) {
            if (tracks.get(i).getTrackNum() == trackNum) {
                tracks.remove(i);
            }
        }
        saveTrackToMusic();
    }

    public void saveTrackToMusic() {
        music.setNote(trackToString());
        music.setTotalTrackNum(tracks.size());
    }

    public void setBpm(int bpm) {
        this.bpm = bpm;
    }

    public int getBpm() {
        return bpm;
    }

    public ArrayList<Track> getTrack() {
        return tracks;
    }

    public ArrayList<String> getDrumTrack() {
        return drumTracks;
    }

    public Music getMusic() {
        return music;
    }

    public String trackToString() {
        String temp = "";
        for (Track track : tracks) {
            temp = temp + track;
        }
        return temp;
    }

    public String drumTrackToString() {
        String temp = "";
        for (String drumTrack : drumTracks) {
            if (temp.equals("")) {// 第一個
                temp = drumTrack;
            } else {
                temp = temp + "@" + drumTrack;
            }
        }
        return temp;
    }
}