package superlines.deicstra;

/*
* CustomCell.java
*
* Created on 18 Август 2007 г., 0:21
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

/** Конструктор создающий слетку по переданным
* координатам и стоимости проходжения
* @param cost - стоимость прохождения
* если стоимость меньше нуля - клетка не проходима
* x, y - координаты
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

//Проходимость клетки
private boolean Passableness = true;
/** Метод информирует о том проходима клетка или нет
* @return true - клетка проходима
* false - клетка не проходима
*/
public boolean isPassable() {return this.Passableness;}

/** Метод устанавливает проходимость клетки
* @param true - клетка проходима
* false - клетка не проходима
*/ 
public void setPassableness(boolean Passableness){
this.Passableness = Passableness;
}

//Положение клетки
private Point Position = new Point();
/**Метод устанавливает координаты клетки
* через переданный обьект Point
*/
public void setPosition (Point Position){
this.Position = Position;
}
/**Метод возвращает координаты клетки
*/
public Point getPosition(){
return this.Position;
}
/**Метод устанавливает координаты клетки
*/
public void setPosition (int X, int Y){
this.Position = new Point(X,Y);
}
/**Метод возвращает абсциссу клетки
*/ 
public int getX(){
return this.Position.x;
}
/**Метод возвращает ординату клетки
*/ 
public int getY(){
return this.Position.y;
}

/**Метод отображает клетку на переданном контексте
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

//Цвет клетки
private Color Color = new Color(255,255,255);
public void setColor(Color Color){
this.Color = Color;
}
public Color getColor() {return this.Color;}
}