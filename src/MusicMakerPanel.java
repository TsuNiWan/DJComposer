import javax.swing.*;
import java.awt.*;

public class MusicMakerPanel extends JPanel {
    private ImageIcon drum = new ImageIcon(new ImageIcon(getClass().getResource("drum-solid.png")).getImage().getScaledInstance(64, 64, Image.SCALE_DEFAULT));

    JLabel bar;
    JScrollPane dashboardScroll;
    JPanel dashboard;
    JPanel musicalBar;
    JLabel musical;
    TrackPanel[] beat;
    JPanel[] list;

    public MusicMakerPanel() {
        setLayout(new BorderLayout());

        bar = new JLabel("music maker");
        add(bar, BorderLayout.NORTH);

        musicalBar = new JPanel(new FlowLayout());
        musicalBar.setPreferredSize(new Dimension( 70,250));
        musical = new JLabel(drum);
        musicalBar.add(musical);
        add(musicalBar, BorderLayout.WEST);

        dashboard = new JPanel(new FlowLayout());
        dashboard.setPreferredSize(new Dimension( 4500,250));
        beat = new TrackPanel[180];
        for (TrackPanel tp : beat) {
            tp = new TrackPanel();
            tp.setPreferredSize(new Dimension( 70,70));
            dashboard.add(tp);
        }
        //dashboard.add(list);
        dashboardScroll = new JScrollPane(dashboard);
        dashboard.setAutoscrolls(true);
        dashboardScroll.setPreferredSize(new Dimension( 400,250));
        add(dashboardScroll, BorderLayout.CENTER);
    }
}
