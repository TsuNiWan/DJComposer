import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;

public class DrumNote extends JLabel{
	private int currentValue = 0;
	private int x,y;
	ArrayList<JLabel> lbNums;
	
	public DrumNote(int x,int y,ArrayList<JLabel> lbNums) {
		Border border = BorderFactory.createLineBorder(Color.darkGray, 3);

		this.x = x;
		this.y = y;
		this.lbNums = lbNums;

		setOpaque(true);
		setBackground(Color.lightGray);
		setBorder(border);
		setBounds(x, y, 60, 60);
		setSize(60,60);
		addMouseListener(new EventListener());	
		
	}
	
	

	public void setCurrentValue(int currentValue) {
		this.currentValue = currentValue;
		if(currentValue==1) {
			setBackground(Color.cyan);
		}else {
			setBackground(Color.lightGray);
		}
	}

	public int getCurrentValue() {
		return currentValue;
	}
	
	
	//event
	class EventListener implements MouseListener{
		@Override
		public void mouseClicked(MouseEvent e) {
			if(currentValue == 0) {
				setBackground(Color.cyan);
				currentValue = 1;
			}else {
				setBackground(Color.lightGray);
				currentValue = 0;
			}
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			for (JLabel b : lbNums) {
				if(b.getX()==x) {
					b.setBackground(Color.white);
					b.setForeground(Color.black);
				}
			}			
		}
		@Override
		public void mouseExited(MouseEvent e) {
			for (JLabel b : lbNums) {
				if(b.getX()==x) {
					b.setBackground(Color.DARK_GRAY);
					b.setForeground(Color.WHITE);
				}
			}			
		}
		@Override
		public void mousePressed(MouseEvent e) {}
		@Override
		public void mouseReleased(MouseEvent e) {}
	}	

}
