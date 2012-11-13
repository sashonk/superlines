package superlines;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Util {
	public static String toString(final Exception e){
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		 e.printStackTrace(pw);
		 return sw.toString();
	}
	
	
	public static void main(String[] a){
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(100, 100, 300, 200);
		
		NewCanvas nc = new NewCanvas();
		nc.setFrame(f);
		
		
		
		f.getContentPane().add(nc);
		f.setVisible(true);
	}
}

class NewCanvas extends Canvas {
	
	private JFrame parent;
	public void setFrame(JFrame f){
		parent = f;
	}
	
	BufferedImage m_img;
	{
		try{
			BufferedImage img = ImageIO.read(new FileInputStream("C:\\icons.jpg"));
			m_img = img;
		//	img.getg
		}
		catch(Exception e){
			System.err.print(e);
		}
	}
	
	@Override
	public void paint(Graphics g){
		g.drawImage(m_img, 0, 0, parent.getWidth(), parent.getHeight(), null);
	}
};