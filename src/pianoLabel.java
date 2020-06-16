import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.util.ArrayList;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

public class pianoLabel extends JLabel {

    private JComboBox instName;
    private String whiteKey[] = { "C", "D", "E", "F", "G", "A", "B" };
    private String blackKey[] = { "C#", "D#", "F#", "G#", "A#" };
    private ArrayList<JLabel> piano;

    public pianoLabel(JComboBox instName, ArrayList<JLabel> piano) {
        this.piano = piano;
        this.instName = instName;
        setBounds(0, 0, 87, 562);
        setIcon(new ImageIcon("Images/piano.png"));
        whiteKeyLabel();
        blackKeyLabel();
    }

    public void whiteKeyLabel() {
        int height = 0;
        for (int i = 0; i < 7; i++) {// C7-C1
            for (int j = 0; j < whiteKey.length; j++) {
                JLabel key = new JLabel(whiteKey[6 - j] + (7 - i));
                key.setFont(new Font("Arial", Font.PLAIN, 10));
                key.setBounds(50, height, 35, 20);
                key.setHorizontalAlignment(SwingConstants.RIGHT);
                add(key);
                height = height + 20;
                piano.add(key);
                key.addMouseListener(new EventListner(key));
            }
        }
    }

    public void blackKeyLabel() {
        int height = 10;
        for (int i = 0; i < 7; i++) {// C7#-C1#
            for (int j = 0; j < blackKey.length; j++) {
                JLabel key = new JLabel(blackKey[4 - j] + (7 - i));
                key.setFont(new Font("Arial", Font.PLAIN, 9));
                key.setForeground(Color.WHITE);
                key.setBounds(23, height, 20, 20);
                key.setHorizontalAlignment(SwingConstants.RIGHT);
                add(key);
                if ((j == 2) || (j == 4)) {
                    height += 40;
                } else {
                    height += 20;
                }
                piano.add(key);
                key.addMouseListener(new EventListner(key));
            }
        }
    }

    class PlayThread implements Runnable {
        private String clickedKey = "";

        public PlayThread(String clickedKey) {
            this.clickedKey = clickedKey;
        }

        @Override
        public void run() {
            Pattern pattern = new Pattern(clickedKey + "q").setInstrument(instName.getSelectedItem().toString());
            Player player = new Player();
            player.play(pattern);
        }
    }

    class EventListner implements MouseListener {
        private JLabel key;

        public EventListner(JLabel key) {
            this.key = key;
        }

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseClicked(MouseEvent e) {
            String clickedKey = key.getText();
            Thread thread = new Thread(new PlayThread(clickedKey));
            thread.start();
        }
    }
}