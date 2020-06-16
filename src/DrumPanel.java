import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

public class DrumPanel extends JPanel {
	private MusicConverter converter;
	private ArrayList<ArrayList<DrumNote>> note;
	private String noteModel[] = { "O", "S", "^"};
	private JPanel drumNotePanel;
	private ArrayList<JLabel> lbNums;
	private JLabel saveLbl;
	private JLabel resetLbl;

	public DrumPanel(MusicConverter converter) {
		this.converter = converter;
		setBackground(Color.DARK_GRAY);
		setBounds(220, 56, 1600, 400);
		setLayout(null);

		saveLbl = new JLabel("存檔");
		saveLbl.setHorizontalAlignment(SwingConstants.CENTER);
		saveLbl.setForeground(Color.BLACK);
		saveLbl.setBackground(Color.ORANGE);
		saveLbl.setBounds(0, 320, 200, 80);
		saveLbl.setOpaque(true);
		add(saveLbl);
		saveLbl.addMouseListener(new EventListener());

		resetLbl = new JLabel("重設");
		resetLbl.setHorizontalAlignment(SwingConstants.CENTER);
		resetLbl.setForeground(Color.BLACK);
		resetLbl.setBackground(Color.ORANGE);
		resetLbl.setBounds(0, 220, 200, 80);
		resetLbl.setOpaque(true);
		add(resetLbl);
		resetLbl.addMouseListener(new EventListener());

		JLabel drumImg = new JLabel(new ImageIcon("Images/drum.png"));
		drumImg.setLocation(0, 10);
		drumImg.setSize(200, 200);
		add(drumImg);

		drumNotePanel = new JPanel();
		drumNotePanel.setBackground(Color.DARK_GRAY);
		drumNotePanel.setBounds(0, 0, converter.getMusic().getMadi()*60*8 + 20, 230);
		drumNotePanel.setPreferredSize(new Dimension(converter.getMusic().getMadi()*60*8 + 20, 230));
		drumNotePanel.setLayout(null);

		initDrumNote(8*converter.getMusic().getMadi());
		stringToNote(converter.getMusic().getDrumNote());
		
		drumNotePanel.repaint();
		JScrollPane scrollPane = new JScrollPane(drumNotePanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(200, 0, 720, 230);
		add(scrollPane);
	}

	private void initDrumNote(int sizeOfNote) {
		lbNums = new ArrayList<JLabel>();

		for (int i = 0; i < sizeOfNote; i++) {
			JLabel lbNum = new JLabel(i+1 + "");
			lbNum.setOpaque(true);
			lbNum.setHorizontalAlignment(SwingConstants.CENTER);
			lbNum.setForeground(Color.WHITE);
			lbNum.setBackground(Color.DARK_GRAY);
			lbNum.setFont(new Font("Arial", Font.BOLD, 12));
			lbNum.setBounds(i * 60, 0, 60, 20);
			drumNotePanel.add(lbNum);
			lbNums.add(lbNum);
		}
		
		note = new ArrayList<ArrayList<DrumNote>>();
		for (int i = 0; i < 3; i++) {
			note.add(new ArrayList<DrumNote>());
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < sizeOfNote; j++) {
				note.get(i).add(new DrumNote(60 * j, 60 * i + 20,lbNums));
			}
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < sizeOfNote; j++) {
				drumNotePanel.add(note.get(i).get(j));
			}
		}
	}

	private void stringToNote(String notes) {
		String step1[] = notes.split("@");
		ArrayList<ArrayList<String>> step2 = new ArrayList<ArrayList<String>>();
		for (int i = 0; i < step1.length; i++) {
			step2.add(new ArrayList<String>());
			for (int j = 0; j < step1[i].length(); j++) {
				step2.get(i).add(step1[i].charAt(j) + "");
			}
		}
		for (int i = 0; i < step2.size(); i++) {
			for (int j = 0; j < step2.get(i).size(); j++) {
				if (step2.get(i).get(j).equals(".")) {
					note.get(i).get(j).setCurrentValue(0);
				} else {
					note.get(i).get(j).setCurrentValue(1);
				}
			}
		}
	}

	public String guiToString() {
		String tem = "";
		for (int i = 0; i < 3; i++) {
			for (DrumNote n : note.get(i)) {
				if (n.getCurrentValue() == 1) {
					tem = tem + noteModel[i];
				} else {
					tem = tem + ".";
				}
			}
			if (!(i == 2)) {
				tem = tem + "@";
			}
		}
		System.out.println(tem);
		return tem;
	}

	public MusicConverter NewMusic(){
		Music music = new Music("Untitled", 1, 8, 120,
				"V0 I[piano] Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri",
				"................................@................................@................................");
		MusicConverter converter = new MusicConverter(music);
		return converter;
	}
	
	class EventListener implements MouseListener{
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
			if(e.getSource()==saveLbl) {
				converter.getMusic().setDrumNote(guiToString());
				converter.musicToDrumArray();
				JOptionPane.showMessageDialog(null, "存檔成功");
			}
			else if(e.getSource()==resetLbl){
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 64; j++) {
						note.get(i).get(j).setCurrentValue(0);
					}
				}
				converter.getMusic().setDrumNote(guiToString());
				converter.musicToDrumArray();
				JOptionPane.showMessageDialog(null, "重設成功");
			}
		}
	}
}
