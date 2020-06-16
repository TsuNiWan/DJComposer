import javax.sound.midi.MidiUnavailableException;
import javax.swing.JFrame;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;
import org.jfugue.realtime.RealtimePlayer;
import org.jfugue.rhythm.Rhythm;

public class MusicPlayFrame extends JFrame {

    MusicConverter converter;
    RealtimePlayer rp1, rp2;
    Player player;

    class PlayThread extends Thread {

        private void playMusic() {
            Pattern p1 = new Pattern(converter.trackToString());
            Rhythm r = new Rhythm();
            String drum = converter.drumTrackToString();
            String dt[] = drum.split("@");

            for (int i = 0; i < dt.length; i++) {
                r.addLayer(dt[i]);
            }

            Pattern p2 = new Pattern(r.getPattern().setTempo(converter.getMusic().getBpm()));

            p1.setTempo(converter.getMusic().getBpm());

            player = new Player();
            player.play(p1, p2);

        }

        public void run() {
            try {
                playMusic();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


    public MusicPlayFrame(MusicConverter converter) {
        this.converter = converter;
        setBounds(0, 0, 10, 10);
        PlayThread thread = new PlayThread();
        thread.start();
    }

    public void stop() {
        player.play("");
        dispose();
    }


    private void playMusic() {
        try {

            Pattern p1 = new Pattern(converter.trackToString());
            Rhythm r = new Rhythm();
            String drum = converter.drumTrackToString();
            String dt[] = drum.split("@");

            for (int i = 0; i < dt.length; i++) {
                r.addLayer(dt[i]);
            }
            Pattern p2 = new Pattern(r.getPattern().setTempo(converter.getMusic().getBpm()));
            p1.setTempo(converter.getMusic().getBpm());


            rp1 = new RealtimePlayer();
            rp1.play(p1);
            rp2 = new RealtimePlayer();
            rp2.play(p2);

        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
    }
}


