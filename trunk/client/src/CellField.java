import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import algorithm.CustomCell;
import algorithm.DeicstraArea;


public class CellField extends Canvas {
	CellField _this;
	
	public CellField(int i,int j)
	{
		p = new Point();
		p.x = i;
		p.y = j;
		_this = this;
		isEmpty = true;
		
		this.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e)
			{
				
			}
		});
	}
	
	public void paint(Graphics g)
	{
		int w = getWidth();
		int h = getHeight();
		//this.setBackground(Color.BLUE);
		g.setColor(color);
		g.fillRect(1, 1, w-2, h-2);
		g.setColor(Color.DARK_GRAY);
		g.drawRect(1, 1, w-3, h-3);
		g.setColor(Color.WHITE);
		g.drawRect(1, 1, w-1, h-1);
		if(isSelected)
		{
			g.setColor(Color.BLACK);
			g.fillRect(w/4, h/4, w/2, h/2);
		}
	}
	
	
	public boolean isEmpty()
	{
		return isEmpty;
	}
	public void setEmpty(boolean empty)
	{
		isEmpty = empty;
		//isSelected = false;
		if(isEmpty==true)
			isSelected = false;
		color = defColor;
		repaint();
	}
	public boolean isSelected()
	{
		return isSelected;
	}
	public void setSelected(boolean sel)
	{
		isSelected = sel;
		repaint();
	}
	public void setColor(Color c)
	{
		color = c;
		isEmpty = false;
		repaint();
		//DeicstraArea.getInstance().makeArea(area)
	}
	public Color getColor()
	{
		return color;
	}
	
	public Point GetP(){
		return p;
	}
	
	private boolean isEmpty;
	private boolean isSelected;
	private Point p;
	final private Color defColor = new Color(200, 200, 200);
	private Color color = defColor;

}

