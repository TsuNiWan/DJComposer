import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.google.gson.Gson;

public class ComposerGUI extends JFrame{
    private ImageIcon play = new ImageIcon(new ImageIcon(getClass().getResource("play-solid.png")).getImage().getScaledInstance(28, 32, Image.SCALE_DEFAULT));
    private ImageIcon stop = new ImageIcon(new ImageIcon(getClass().getResource("stop-solid.png")).getImage().getScaledInstance(28, 32, Image.SCALE_DEFAULT));

    protected static final String String = null;
    private JPanel contentPane;
    private MusicConverter converter;
    private JPanel panelMain;
    private JList list;
    private DefaultListModel listModel;
    private MusicPlayFrame playMusicBt;

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ComposerGUI frame = new ComposerGUI();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ComposerGUI() {
        super("DJComposer");

        Border border = BorderFactory.createLineBorder(Color.gray, 3);

        panelMain = new JPanel();
        getContentPane().add(panelMain);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1150, 550);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu mnFile = new JMenu("Track File");
        menuBar.add(mnFile);

        JMenu record = new JMenu("VoiceRecord");
        menuBar.add(record);


        JMenuItem mntmNew = new JMenuItem("NEW");
        mnFile.add(mntmNew);
        mntmNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new newFileFrame(listModel);
            }
        });

        JMenuItem mntmSave = new JMenuItem("SAVE");
        mnFile.add(mntmSave);
        mntmSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveFile();
                JOptionPane.showMessageDialog(null, "Save Success");
            }
        });

        JMenuItem mntmDelete = new JMenuItem("DELETE");
        mnFile.add(mntmDelete);
        mntmDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteFile(list.getSelectedValue().toString());
                listModel.remove(list.getSelectedIndex());
                JOptionPane.showMessageDialog(null, "Delete Success");
            }
        });

        JMenuItem mntmExit = new JMenuItem("EXIT");
        mnFile.add(mntmExit);
        mntmExit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });


        contentPane = new JPanel();
        contentPane.setBackground(Color.DARK_GRAY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panelTop = new JPanel();
        panelTop.setBackground(SystemColor.controlDkShadow);
        panelTop.setBounds(220, 0, 1150, 46);
        panelTop.setOpaque(true);
        panelTop.setBackground(Color.gray);
        contentPane.add(panelTop);
        panelTop.setLayout(null);

        JButton btnPlay = new JButton(play);
        btnPlay.setOpaque(true);
        btnPlay.setBorder(border);
        btnPlay.setBackground(Color.white);
        btnPlay.setBounds(0, 0, 46, 46);
        btnPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playMusicBt = new MusicPlayFrame(converter);
            }
        });

        panelTop.add(btnPlay);

        JButton btnStop = new JButton(stop);
        btnStop.setOpaque(true);
        btnStop.setBorder(border);
        btnStop.setBackground(Color.white);
        btnStop.setBounds(49, 0, 46, 46);
        btnStop.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                playMusicBt.stop();
            }
        });
        panelTop.add(btnStop);

        JSpinner spinnerBPM = new JSpinner();
        spinnerBPM.setModel(new SpinnerNumberModel(new Integer(120), null, null, new Integer(1)));
        spinnerBPM.setFont(new Font("Arial", Font.BOLD, 24));
        spinnerBPM.setForeground(Color.WHITE);
        spinnerBPM.setBackground(Color.DARK_GRAY);
        spinnerBPM.setBounds(147, 0, 132, 46);
        panelTop.add(spinnerBPM);
        spinnerBPM.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent arg0) {
                int tem = Integer.parseInt(spinnerBPM.getValue().toString());
                System.out.println(tem);
                converter.setBpm(tem);
                converter.getMusic().setBpm(tem);
            }
        });

        JComboBox cmbTrack = new JComboBox();
        cmbTrack.setFont(new Font("Arial", Font.BOLD, 17));
        cmbTrack.setBounds(320, 0, 105, 46);
        panelTop.add(cmbTrack);

        JButton btnAddTrack = new JButton("新增音軌");
        btnAddTrack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                converter.addTrack();
                cmbTrack.removeAllItems();
                cmbTrack.addItem("---");
                for (Track t : converter.getTrack()) {
                    cmbTrack.addItem(t.getTrackNum());
                }
                cmbTrack.addItem("Drum");

            }
        });
        btnAddTrack.setFont(new Font("Arial", Font.BOLD, 14));
        btnAddTrack.setForeground(Color.BLACK);
        btnAddTrack.setBackground(Color.WHITE);
        btnAddTrack.setBounds(530, -1, 100, 46);
        panelTop.add(btnAddTrack);

        JButton btnRemovetrack = new JButton("刪除音軌");
        btnRemovetrack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!(cmbTrack.getSelectedItem().toString().equals("---"))) {
                    remove(panelMain);
                    int delNum = Integer.parseInt(cmbTrack.getSelectedItem().toString());
                    converter.removeTrack(delNum);
                    cmbTrack.removeAllItems();
                    cmbTrack.addItem("---");
                    for (Track t : converter.getTrack()) {
                        cmbTrack.addItem(t.getTrackNum());
                    }
                    cmbTrack.addItem("Drum");
                }

            }
        });
        btnRemovetrack.setForeground(Color.BLACK);
        btnRemovetrack.setFont(new Font("Arial", Font.BOLD, 14));
        btnRemovetrack.setBackground(Color.WHITE);
        btnRemovetrack.setBounds(630, -1, 100, 46);
        panelTop.add(btnRemovetrack);

        JLabel lblBpm = new JLabel("BPM");
        lblBpm.setFont(new Font("Arial", Font.BOLD, 12));
        lblBpm.setForeground(Color.WHITE);
        lblBpm.setBounds(107, 12, 39, 21);
        panelTop.add(lblBpm);

        JLabel lblTrack = new JLabel("音軌");
        lblTrack.setForeground(Color.WHITE);
        lblTrack.setFont(new Font("Arial", Font.BOLD, 12));
        lblTrack.setBounds(290, 12, 39, 21);
        panelTop.add(lblTrack);

        JButton btnLoadTrack = new JButton("讀取音軌");
        btnLoadTrack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (cmbTrack.getSelectedItem().equals("Drum")) {
                    remove(panelMain);
                    panelMain = new DrumPanel(converter);
                    getContentPane().add(panelMain);
                    repaint();
                    revalidate();
                } else if ((cmbTrack.getSelectedItem().equals("---"))) {
                    remove(panelMain);
                    repaint();
                } else {
                    remove(panelMain);
                    panelMain = new TrackPanel(converter, Integer.parseInt(cmbTrack.getSelectedItem().toString()));
                    getContentPane().add(panelMain);
                    repaint();
                    revalidate();
                }
            }
        });
        btnLoadTrack.setForeground(Color.BLACK);
        btnLoadTrack.setFont(new Font("Arial", Font.BOLD, 14));
        btnLoadTrack.setBackground(Color.WHITE);
        btnLoadTrack.setBounds(430, -1, 100, 46);
        panelTop.add(btnLoadTrack);

        JSpinner spinnerBar = new JSpinner();
        spinnerBar.setForeground(Color.WHITE);
        spinnerBar.setFont(new Font("Arial", Font.BOLD, 24));
        spinnerBar.setBackground(Color.DARK_GRAY);
        spinnerBar.setBounds(770, 0, 132, 46);
        panelTop.add(spinnerBar);
        spinnerBar.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent arg0) {
                converter.getMusic().setMadi(Integer.parseInt(spinnerBar.getValue().toString()));
            }
        });

        JLabel lblBar = new JLabel("Bar");
        lblBar.setForeground(Color.WHITE);
        lblBar.setFont(new Font("Arial", Font.BOLD, 12));
        lblBar.setBounds(740, 12, 39, 21);
        panelTop.add(lblBar);

        JPanel panelFile = new JPanel();
        panelFile.setBounds(0, 0, 208, 500);
        contentPane.add(panelFile);
        panelFile.setLayout(null);

        String[] fileNames = getFilenames();

        listModel = new DefaultListModel();
        for (int i = 0; i < fileNames.length; i++) {
            listModel.addElement(fileNames[i]);
        }

        list = new JList(listModel);
        list.setForeground(Color.WHITE);
        list.setFont(new Font("Arial", Font.BOLD, 18));
        list.setBorder(border);
        list.setBackground(Color.DARK_GRAY);
        list.setBounds(5, 5, 198, 490);
        panelFile.add(list);
        list.addMouseListener(new MouseListener() {

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
                if (e.getClickCount() == 2) {
                    System.out.println();
                    Gson gson = new Gson();
                    String data = readFile(list.getSelectedValue().toString());
                    Music music = gson.fromJson(data, Music.class);
                    converter = new MusicConverter(music);

                    spinnerBPM.setValue(converter.getMusic().getBpm());
                    cmbTrack.removeAllItems();
                    cmbTrack.addItem("---");
                    for (Track t : converter.getTrack()) {
                        cmbTrack.addItem(t.getTrackNum());
                    }
                    cmbTrack.addItem("Drum");
                    spinnerBar.setValue(converter.getMusic().getMadi());
                    remove(panelMain);
                    repaint();
                    revalidate();
                    JOptionPane.showMessageDialog(null, "Load Success");
                }
            }
        });

        setVisible(true);
    }

    private String readFile(String filename) {
        String test = "";
        try {
            FileReader file_reader = new FileReader(new File("audioCompose/" + filename));
            int cur = 0;
            while ((cur = file_reader.read()) != -1) {
                test = test + (char) cur;
            }
            file_reader.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
        return test;
    }

    private void newFile(String fileName) {
        Gson gson = new Gson();
        String result = gson.toJson(new Music(fileName, 1, 8, 120, "V0 I[piano] Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri", "................................@................................@................................"));

        try {
            OutputStream output = new FileOutputStream(new File("audioCompose/" + fileName));
            byte[] by = result.getBytes();
            output.write(by);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    private void saveFile() {
        Gson gson = new Gson();
        String result = gson.toJson(converter.getMusic());

        try {
            OutputStream output = new FileOutputStream(new File("audioCompose/" + converter.getMusic().getFileName()));
            byte[] by = result.getBytes();
            output.write(by);

        } catch (Exception e) {
            e.getStackTrace();
        }

    }

    private void deleteFile(String fileName) {
        File file = new File("audioCompose/" + fileName);
        file.delete();
    }

    private String[] getFilenames() {
        String path = "audioCompose/";
        File dirFile = new File(path);
        File[] fileList = dirFile.listFiles();
        String result = "";
        for (File tempFile : fileList) {
            if (tempFile.isFile()) {
                String tempPath = tempFile.getParent();
                String tempFileName = tempFile.getName();
                if (tempFileName.substring(tempFileName.length() - 6, tempFileName.length()).equals(".music")) {
                    result = result + "@" + tempFileName;
                }
            }
        }
        return result.split("@");
    }
}
