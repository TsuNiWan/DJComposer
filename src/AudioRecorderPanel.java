import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AudioRecorderPanel extends JPanel {
    private ImageIcon mic = new ImageIcon(new ImageIcon(getClass().getResource("microphone-solid.png")).getImage().getScaledInstance(22, 32, Image.SCALE_DEFAULT));
    private ImageIcon play = new ImageIcon(new ImageIcon(getClass().getResource("play-solid.png")).getImage().getScaledInstance(28, 32, Image.SCALE_DEFAULT));
    private ImageIcon pause = new ImageIcon(new ImageIcon(getClass().getResource("pause-solid.png")).getImage().getScaledInstance(28, 32, Image.SCALE_DEFAULT));
    private ImageIcon stop = new ImageIcon(new ImageIcon(getClass().getResource("stop-solid.png")).getImage().getScaledInstance(28, 32, Image.SCALE_DEFAULT));
    private ImageIcon save = new ImageIcon(new ImageIcon(getClass().getResource("save-regular.png")).getImage().getScaledInstance(28, 32, Image.SCALE_DEFAULT));

    JButton[] recorderButton = new JButton[4];
    JButton[] playerButton = new JButton[4];
    ImageIcon[] recorderButtonText = {mic, pause, play, stop};
    ImageIcon[] playerButtonText = {mic, pause, play, stop};
    JLabel bar;
    JLabel recordLabel;
    JPanel record;
    JLabel playerLabel;
    JPanel player;
    JPanel file;
    JLabel fileLabel;
    JTextField fileName;
    JButton saveFile;
    AudioRecorder recorder;


    public AudioRecorderPanel() {
        recorder = new AudioRecorder();
        setLayout(new GridLayout(6, 1));

        bar = new JLabel("錄音");
        bar.setFont(new Font("Calibri", Font.PLAIN, 32));
        bar.setBorder(new EmptyBorder(10, 10, 20, 10));
        add(bar);

        recordLabel = new JLabel("錄音選項");
        add(recordLabel);

        record = new JPanel(new GridLayout(1, 4));
        for (int i = 0; i < recorderButton.length; i++) {
            recorderButton[i] = new JButton(recorderButtonText[i]);
            record.add(recorderButton[i]);
            recorderButton[i].addActionListener(new MyListener());
            if (i > 0) recorderButton[i].setEnabled(false);
        }
        add(record);

        playerLabel = new JLabel("播放選項");
        add(playerLabel);

        player = new JPanel(new GridLayout(1, 5));
        for (int i = 0; i < playerButton.length; i++) {
            playerButton[i] = new JButton(playerButtonText[i]);
            player.add(playerButton[i]);
            playerButton[i].addActionListener(new MyListener());
            playerButton[i].setEnabled(false);
        }
        add(player);

        file = new JPanel(new GridLayout(1, 2));
        fileLabel = new JLabel("檔名");
        file.add(fileLabel);
        fileName = new JTextField("Default");
        file.add(fileName);
        saveFile = new JButton(save);
        saveFile.addActionListener(new MyListener());
        file.add(saveFile);
        add(file);
    }

    private class MyListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == recorderButton[0]) {
                System.out.println("R1");
                recorderButton[0].setEnabled(false);
                recorderButton[1].setEnabled(true);
                recorderButton[2].setEnabled(false);
                recorderButton[3].setEnabled(true);
                //recorder.capture();
            } else if (e.getSource() == recorderButton[1]) {
                recorderButton[1].setEnabled(false);
                System.out.println("R2");
                //recorder.pauseRecord();
            } else if (e.getSource() == recorderButton[2]) {
                System.out.println("R3");
                //recorder.resumeRecord();
            } else if (e.getSource() == recorderButton[3]) {
                System.out.println("R4");
                //recorder.stopRecord();
            } else if (e.getSource() == playerButton[0]) {
                System.out.println("P1");
                //recorder.play();
            } else if (e.getSource() == playerButton[1]) {
                System.out.println("P2");
                //recorder.pausePlay();
            } else if (e.getSource() == playerButton[2]) {
                System.out.println("P3");
                //recorder.resumePlay();
            } else if (e.getSource() == playerButton[3]) {
                System.out.println("P4");
                //recorder.stopPlay();
            } else if (e.getSource() == saveFile) {
                System.out.println("File " + fileName.getText() + " Save");
                //recorder.save(fileName.getText());
            }
        }
    }
}
