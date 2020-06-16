import javax.swing.*;
import java.awt.*;

public class MusicMakerPanel extends JPanel {
    JLabel bar;
    JScrollPane dashboard;
    TrackPanel[] test;
    JPanel list;

    public MusicMakerPanel() {
        setLayout(new BorderLayout());

        bar = new JLabel("music maker");
        add(bar, BorderLayout.NORTH);


        list = new JPanel(new GridLayout(4, 60));
        list.setPreferredSize(new Dimension( 1000,300));
        test = new TrackPanel[60];
        for (TrackPanel tp : test) {
            tp = new TrackPanel();
            list.add(tp);
        }
        dashboard = new JScrollPane(list);
        list.setAutoscrolls(true);
        dashboard.setPreferredSize(new Dimension( 400,300));
        add(dashboard, BorderLayout.CENTER);
    }
}
