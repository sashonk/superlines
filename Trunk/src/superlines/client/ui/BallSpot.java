package superlines.client.ui;


import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class BallSpot extends JPanel{
	
	@Override
	public void paint(Graphics g){
		super.paint(g);
		if(m_clicked){
			g.setColor(Color.black);
			g.fillRect(this.getWidth()/10, this.getHeight()/10, this.getWidth()/5, this.getHeight()/5);
		}
	}

	public BallSpot(int x, int y){
		m_x = x;
		m_y = y;
	}
	
	public int getXCoor(){
		return m_x;
	}
	
	public int getYCoor(){
		return m_y;
	}
	
	public void setClicked(boolean value){
		m_clicked = value;
		repaint();
	}
	
	public boolean isClicked(){
		return m_clicked;
	}
	
	private int m_x;
	private int m_y;
	private boolean m_clicked;
}
