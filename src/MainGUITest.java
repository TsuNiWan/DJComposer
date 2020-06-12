import javax.swing.JFrame;

public class MainGUITest {
    public static void main(String[] args) {
        MainGUI myFrame = new MainGUI();
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setSize(600, 400); // set frame size
        myFrame.setVisible(true); // display frame
    }
}
