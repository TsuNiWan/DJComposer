import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI extends JFrame {
    private ImageIcon icon = new ImageIcon(new ImageIcon(getClass().getResource("icon.png")).getImage().getScaledInstance(62, 64, Image.SCALE_DEFAULT));

    JPanel tabBar;
    JButton[] tabButton = new JButton[3];
    String[] tabButtonText = {"首頁", "錄音", "編曲"};
    JPanel home;
    JLabel title;
    AudioRecorderPanel record;
    MusicMakerPanel maker;

    public MainGUI() {
        super("DJcomposer");
        setLayout(new BorderLayout());

        tabBar = new JPanel(new GridLayout(8, 1));
        for (int i = 0; i < tabButton.length; i++) {
            tabButton[i] = new JButton(tabButtonText[i]);
            tabBar.add(tabButton[i]);
            tabButton[i].addActionListener(new MyListener());
        }

        home = new JPanel(new FlowLayout());
        title = new JLabel("DJComposer", icon, JLabel.LEADING);
        title.setFont(new Font("Calibri", Font.PLAIN, 48));
        home.add(title);

        record = new AudioRecorderPanel();

        maker = new MusicMakerPanel();

        add(tabBar, BorderLayout.WEST);
        add(home, BorderLayout.CENTER);

    }

    private class MyListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == tabButton[0]) {
                add(home, BorderLayout.CENTER);
                home.setVisible(true);
                record.setVisible(false);
                maker.setVisible(false);
            } else if (e.getSource() == tabButton[1]) {
                add(record, BorderLayout.CENTER);
                home.setVisible(false);
                record.setVisible(true);
                maker.setVisible(false);
            } else if (e.getSource() == tabButton[2]) {
                add(maker, BorderLayout.CENTER);
                home.setVisible(false);
                record.setVisible(false);
                maker.setVisible(true);
            }
        }
    }
}