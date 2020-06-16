import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class pianoNoteLabel extends JLabel{
    private int x,y,noteIndex,noteLen;
    private boolean activate;
    private ImageIcon defaultImg=new ImageIcon("Images/noteNotSelect.png");
    private ImageIcon beginImg=new ImageIcon("Images/noteBegin.png");
    private ImageIcon midImg=new ImageIcon("Images/noteMid.png");
    private ImageIcon endImg=new ImageIcon("Images/noteEnd.png");
    private ImageIcon singleNote=new ImageIcon("Images/noteSingle.png");


    public pianoNoteLabel(int x,int y) {
        this.x = x;
        this.y = y;
        setIcon(defaultImg);
        setBounds(x, y, 15, 20);
    }

    public int getNoteIndex() {
        return noteIndex;
    }

    public int getNoteLen() {
        return noteLen;
    }

    public void changeToDefaultImg() {
        activate = false;
        noteIndex = 0;
        noteLen=0;
        setIcon(defaultImg);
    }

    public void changeToBeginImg(int noteLen) {
        activate = true;
        noteIndex = 1;
        this.noteLen=noteLen;
        setIcon(beginImg);
    }

    public void changeToMidImg() {
        activate = true;
        noteIndex = 2;
        noteLen=0;
        setIcon(midImg);
    }

    public void changeToEndImg() {
        activate = true;
        noteIndex = 3;
        noteLen=0;
        setIcon(endImg);
    }

    public void changeToSingleNote() {
        activate = true;
        noteIndex = 4;
        noteLen=1;
        setIcon(singleNote);
    }
}