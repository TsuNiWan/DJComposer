import javax.swing.*;
import java.awt.*;

public class MusicMakerPanel extends JPanel {
    JLabel bar;
    TrackPanel[] test;
    JPanel list;

    public MusicMakerPanel() {
        setLayout(new GridLayout(6, 1));

        bar = new JLabel("music maker");
        add(bar);

        list = new JPanel(new GridLayout(1, 6));
        test = new TrackPanel[6];
        for (TrackPanel tp : test) {
            tp = new TrackPanel();
            list.add(tp);
        }
        add(list);
    }
}
