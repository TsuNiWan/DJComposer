import javax.swing.*;
import java.awt.Color;
import javax.swing.border.Border;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TrackPanel extends JLabel{
    private boolean activate = false;

    public TrackPanel(){
        super("");
        setOpaque(true);
        Border border = BorderFactory.createLineBorder(Color.darkGray, 3);

        // set the border of this component
        setBackground(Color.lightGray);
        setBorder(border);
        setSize(100,100);
        addMouseListener(new EventListener());
    }

    private class EventListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            if(activate) {
                setBackground(Color.lightGray);
            }else {
                setBackground(Color.YELLOW);
            }
            activate = !activate;
            System.out.println((activate)?"True":"False");
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }
    }
}
