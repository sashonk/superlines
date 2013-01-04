package dialog;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;

public class MyButton extends JButton{

	/**
	 * 
	 */
	protected static final long serialVersionUID = 1L;
	//protected int coeff = 3;
	protected String text = null;
	protected boolean pressed;
	protected float lower = 0.2f;
	protected float  higher = 0.8f;
	protected Image icon = null;
	
	public MyButton(){
		addMouseListeners();
	}
	
	public MyButton(String text) {
		addMouseListeners();
		this.text = text;
	}
	
	public void setText(String text){
		this.text = text;
	}
	
	public void setIcon(String iconPath){
		try{
			icon = ImageIO.read(new File(iconPath));
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	private void addMouseListeners(){
		this.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e)
			{
				pressed = true;
				higher = .5f;
				lower = .4f;
				
			}
			
			public void mouseReleased(MouseEvent e)
			{
				pressed = false;
				lower = .2f;
				higher = .8f;
			}
		});
	}
	
	public void paint(Graphics g)
	{

		Graphics2D g2d = (Graphics2D)g;
		super.paint(g2d);
		

		Color shadowed = pressed ? Color.white : Color.black;
		Color highlighted = pressed ? Color.black : Color.white;
		g2d.setColor(shadowed);
		g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		g2d.setColor(highlighted);
		g2d.drawLine(0, 0, this.getWidth(), 0);
		g2d.drawLine(0, 0, 0, this.getHeight());


		Color c1 = new Color(higher, higher, higher);
		Color c2 = new Color(lower, lower, lower);
		
		GradientPaint gp = new GradientPaint(50, 0, c1, 50, this.getHeight(), c2);
		g2d.setPaint(gp);
		g2d.fillRect(1, 1, this.getWidth()-2, this.getHeight()-2);	
		
		if(icon!=null){
			g2d.drawImage(icon, this.getWidth()/2-icon.getWidth(null)/2, this.getHeight()/2-icon.getHeight(null)/2, null);
		}
		
		if(text!=null){
			FontMetrics fm = g2d.getFontMetrics();
			Rectangle2D bounds = fm.getStringBounds(text, 0, text.length(), g2d);
			double width = bounds.getWidth();
			double height = bounds.getHeight();
			
			g2d.setColor(Color.white);
			g2d.drawString(text, this.getWidth()/2-(int)width/2, this.getHeight()/2+this.getFont().getSize()/4);
	

		}

	}
}
