package algorithm;

/*
* CustomCell.java
*
* Created on 18 ������ 2007 �., 0:21
*
*/

/**
*
* @author _shef_
*/
import java.awt.*;

public class CustomCell {

/** Creates a new instance of CustomCell */
public CustomCell() {
}

/** ����������� ��������� ������ �� ����������
* ����������� � ��������� �����������
* @param cost - ��������� �����������
* ���� ��������� ������ ���� - ������ �� ���������
* x, y - ����������
*/
public CustomCell(int cost, int x, int y) {
this.Cost = Math.abs(cost);
this.setColor(new Color(100,100,(int)(255/Cost)));
if (cost < 0) {
this.Passableness = false;
this.setColor(Color.BLACK);
} else {
this.Passableness = true;
}
this.setPosition(x, y);


}

public double Cost = 1.0;

//������������ ������
private boolean Passableness = true;
/** ����� ����������� � ��� ��������� ������ ��� ���
* @return true - ������ ���������
* false - ������ �� ���������
*/
public boolean isPassable() {return this.Passableness;}

/** ����� ������������� ������������ ������
* @param true - ������ ���������
* false - ������ �� ���������
*/ 
public void setPassableness(boolean Passableness){
this.Passableness = Passableness;
}

//��������� ������
private Point Position = new Point();
/**����� ������������� ���������� ������
* ����� ���������� ������ Point
*/
public void setPosition (Point Position){
this.Position = Position;
}
/**����� ���������� ���������� ������
*/
public Point getPosition(){
return this.Position;
}
/**����� ������������� ���������� ������
*/
public void setPosition (int X, int Y){
this.Position = new Point(X,Y);
}
/**����� ���������� �������� ������
*/ 
public int getX(){
return this.Position.x;
}
/**����� ���������� �������� ������
*/ 
public int getY(){
return this.Position.y;
}

/**����� ���������� ������ �� ���������� ���������
*/
public void paint(Graphics g){
int x = g.getClipBounds().x + 20;
int y = g.getClipBounds().y + 20;
g.setColor(this.Color);
g.fillRect(x+getX()*2, y+getY()*2, 2, 2);
}

public String toString(){
return ("("+ getX() + ";" + getY() +"):\n"
+ "Passableness: " + isPassable() + "\n"
);
}

//���� ������
private Color Color = new Color(255,255,255);
public void setColor(Color Color){
this.Color = Color;
}
public Color getColor() {return this.Color;}
}