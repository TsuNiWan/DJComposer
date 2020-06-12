import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AudioRecorderPanel extends JPanel {
    JButton[] recorderButton = new JButton[4];
    JButton[] playerButton = new JButton[4];
    String[] recorderButtonText = {"開始", "暫停", "繼續", "停止"};
    String[] playerButtonText = {"開始", "暫停", "繼續", "停止"};
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

        bar = new JLabel("record");
        add(bar);

        recordLabel = new JLabel("錄音選項");
        add(recordLabel);

        record = new JPanel(new GridLayout(1, 4));
        for (int i = 0; i < recorderButton.length; i++) {
            recorderButton[i] = new JButton(recorderButtonText[i]);
            record.add(recorderButton[i]);
            recorderButton[i].addActionListener(new MyListener());
        }
        add(record);

        playerLabel = new JLabel("播放選項");
        add(playerLabel);

        player = new JPanel(new GridLayout(1, 5));
        for (int i = 0; i < playerButton.length; i++) {
            playerButton[i] = new JButton(playerButtonText[i]);
            player.add(playerButton[i]);
            playerButton[i].addActionListener(new MyListener());
        }
        add(player);

        file = new JPanel(new GridLayout(1, 2));
        fileLabel = new JLabel("檔名");
        file.add(fileLabel);
        fileName = new JTextField("Default");
        file.add(fileName);
        saveFile = new JButton("存檔");
        saveFile.addActionListener(new MyListener());
        file.add(saveFile);
        add(file);
    }

    private class MyListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == recorderButton[0]) {
                System.out.println("R1");
                recorder.capture();
            } else if (e.getSource() == recorderButton[1]) {
                System.out.println("R2");
                recorder.pauseRecord();
            } else if (e.getSource() == recorderButton[2]) {
                System.out.println("R3");
                recorder.resumeRecord();
            } else if (e.getSource() == recorderButton[3]) {
                System.out.println("R4");
                recorder.stopRecord();
            } else if (e.getSource() == playerButton[0]) {
                System.out.println("P1");
                recorder.play();
            } else if (e.getSource() == playerButton[1]) {
                System.out.println("P2");
                recorder.pausePlay();
            } else if (e.getSource() == playerButton[2]) {
                System.out.println("P3");
                recorder.resumePlay();
            } else if (e.getSource() == playerButton[3]) {
                System.out.println("P4");
                recorder.stopPlay();
            } else if (e.getSource() == saveFile) {
                System.out.println("File " + fileName.getText() + " Save");
                recorder.save(fileName.getText());
            }
        }
    }
}
