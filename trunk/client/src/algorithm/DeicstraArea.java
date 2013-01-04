package algorithm;

/*
* DeicstraArea.java
*
* Created on 21 ������ 2007 �., 22:54
*
*/

/**
*
* @author 222
*/
import java.awt.*;
import java.util.*;
import java.lang.*;

public class DeicstraArea {

/** Creates a new instance of DeicstraArea */
private DeicstraArea() { 
}


private static DeicstraArea instance = null;
/** ����� ��������� ��� ���������� ������� Singletton
*/
public static DeicstraArea getInstance(){
if (instance == null) instance = new DeicstraArea();
return instance;
}
/** ����� ������� ���������� ����� ����� ����������
* � ���� ������� �������� ��������� ����������� �����
* ��� ������ ����� ������ ������ � ������ ������
* �� ����� ������
* @param ������ ����������� � ������� area[x][y]
* ������������� �������� ��������� ��������������
* ��� ������������ ������
*/
public void makeArea(int [][] area){
	this.area.clear();
if (area.length == 0) return;
this.width = area.length;
this.heigth = area[0].length;
for(int y=0; y<heigth; y++)
for(int x=0; x<width; x++){
CustomCell cell = new CustomCell(area[x][y], x, y);

this.area.add(cell);
} 
}

/** ����� ���������� ������ �� ������ �����������
* ������ ����� �� ���������� ����������� x � y
*/
public CustomCell getCell(int x, int y) {
return ((CustomCell)this.area.get(y*this.width + x));
}

/** ����� ���������� ������ �� ������ ��������
* �� ���������� ����������� � ����� ������ ��������
* @param x, y - ���������� ������
* area - ������ �� ��������� ������-�����
* ����� ��������� �� ������ ������ DeicstraCell

*/
private DeicstraCell getCell(int x, int y, ArrayList <DeicstraCell> area) {
return ((DeicstraCell) area.get(y*this.width + x));
}

/** ����� ���������� ������ ��������� �� ������
* �� ������� ����� ������� ����� (�� ������)
* �� ���������� ������ �� ������ ��
* ����������� ������ �����
* @param cell - ������ � ������� ���������� �����
*/
public ArrayList getEnvironment(CustomCell cell){
return getEnvironment(cell, ENVIROMENT_MODEL_CROSS);
}

/** ����� ���������� ������ ��������� �� ������ ��������
* �� ������� ����� ������� ����� (�� ������)
* �� ���������� ������ ��������
* @param cell - ������ � ������� ���������� �����
* area - ������ �� ��������� ������-�����
* ����� ��������� �� ������ ������ DeicstraCell
*/
private ArrayList getEnvironment(DeicstraCell cell, ArrayList <DeicstraCell> area){
return getEnvironment(cell, area, ENVIROMENT_MODEL_CROSS);
}

/** ����� ���������� ������ ��������� �� ������
* �� ������� ����� ������� �����
* � ������������ � ���������� ������� (�����,��������)
* �� ���������� ������
* @param cell - ������ � ������� ���������� �����
* enviromentModel - ���� �� �������:
* �������� - ENVIROMENT_MODEL_SNOWFLAKE = 1;
* ����� - ENVIROMENT_MODEL_CROSS = 2; 
*/
public ArrayList getEnvironment(CustomCell cell, int enviromentModel) {
int x = cell.getX();
int y = cell.getY();
if ((x >= this.width)|| (y >= this.heigth)) return new ArrayList();
ArrayList tempAL = new ArrayList();

//Up
if (y != 0) tempAL.add(getCell( x, y-1 ));
//Right 
if (x != this.width-1) tempAL.add(getCell( x+1, y ));
//Down
if (y != this.heigth-1) tempAL.add(getCell( x, y+1 ));
//Left
if (x != 0) tempAL.add(getCell( x-1, y ));

if (enviromentModel == ENVIROMENT_MODEL_SNOWFLAKE){
//Left-Up
if ((x != 0)&&(y != 0)) tempAL.add(getCell( x-1, y-1 ));
//Down-Left
if ((y != this.heigth-1)&&(x != 0)) tempAL.add(getCell( x-1, y+1 ));
//Right-Down
if ((x != this.width-1)&&(y != this.heigth-1)) tempAL.add(getCell( x+1, y+1 ));
//Up-Right
if ((y != 0)&&(x != this.width-1)) tempAL.add(getCell( x+1, y-1 ));
}
return tempAL;
}

/** ����� ���������� ������ ��������� �� ������ ��������
* �� ������� ����� ������� �����
* � ������������ � ���������� ������� (�����,��������)
* �� ���������� ������ ��������
* @param cell - ������ � ������� ���������� �����
* area - ������ �� ��������� ������-�����
* ����� ��������� �� ������ ������ DeicstraCell
* enviromentModel - ���� �� �������:
* �������� - ENVIROMENT_MODEL_SNOWFLAKE = 1;
* ����� - ENVIROMENT_MODEL_CROSS = 2;
*/ 
private ArrayList getEnvironment(DeicstraCell Cell, ArrayList <DeicstraCell> area, int enviromentModel) {
int x = Cell.getX();
int y = Cell.getY();
if ((x >= this.width)|| (y >= this.heigth)) return new ArrayList();
ArrayList tempAL = new ArrayList();

//Up
if (y != 0) tempAL.add(getCell( x, y-1, area ));
//Right 
if (x != this.width-1) tempAL.add(getCell( x+1, y, area ));
//Down
if (y != this.heigth-1) tempAL.add(getCell( x, y+1, area ));
//Left
if (x != 0) tempAL.add(getCell( x-1, y, area ));

if (enviromentModel == ENVIROMENT_MODEL_SNOWFLAKE){
//Left-Up
if ((x != 0)&&(y != 0)) tempAL.add(getCell( x-1, y-1, area ));
//Down-Left
if ((y != this.heigth-1)&&(x != 0)) tempAL.add(getCell( x-1, y+1, area ));
//Right-Down
if ((x != this.width-1)&&(y != this.heigth-1)) tempAL.add(getCell( x+1, y+1, area ));
//Up-Right
if ((y != 0)&&(x != this.width-1)) tempAL.add(getCell( x+1, y-1, area ));
}
return tempAL;
} 

/** ����� ���������� ���������� ���������� ������
*/
public Point getPosition(CustomCell cell) {
return cell.getPosition();
}

/** ����� ���������� ������ ��������� �� ���������� ������
* �� ������� ����� ������� �����
* � ������������ � ���������� ������� (�����,��������)
* �� ���������� ������
* cell - ������ � ������� ���������� �����
*/ 
public ArrayList getCleanEnvironment(CustomCell cell){
ArrayList tempAL = this.getEnvironment(cell);
ArrayList outAL = new ArrayList(); 
Iterator tempI = tempAL.iterator();
CustomCell tempCC;
while( tempI.hasNext()) {
tempCC = (CustomCell)tempI.next();
if (tempCC.isPassable()) outAL.add(tempCC);
}
return outAL;
}
/** ����� ���������� ������ ��������� �� ������������ ������ ��������
* �� ������� ����� ������� �����
* � ������������ � ���������� ������� (�����,��������)
* �� ���������� ������ ��������
* @param cell - ������ � ������� ���������� �����
* area - ������ �� ��������� ������-�����
* ����� ��������� �� ������ ������ DeicstraCell
*/ 
private ArrayList getCleanEnvironment(DeicstraCell cell, ArrayList area){ 
ArrayList tempAL = this.getEnvironment(cell, area);
ArrayList outAL = new ArrayList();

Iterator tempI = tempAL.iterator();
DeicstraCell tempDC;
while( tempI.hasNext()) {
tempDC = (DeicstraCell)tempI.next();
if ( (tempDC.getStatus() == tempDC.STATUS_NOVISITED)
)
outAL.add(tempDC);
}
return outAL;
}

/** ����� ��������� �������� ������ ����
* � ���������� ������� - ������, ���������
* �� ��������� �����, ������� ���������� ��������,
* ��� �� ��������� �� ����� startCell
* �� ����� finishCell
* @param startPoint - ��������� ����� ����
* finishPoint - �������� ����� ����
* @return ���������� ArrayList ��������� �� �������� Point
* ���� ���� �� ������ ���������� null 
*/
public ArrayList findWay(Point startPoint, Point finishPoint) throws NoWayException {
//������� ��������� �������
ArrayList <DeicstraCell> tempArea = new ArrayList <DeicstraCell> ();
ArrayList <DeicstraCell> BarrierList = new ArrayList <DeicstraCell> ();
DeicstraCell STARTCELL = null;
DeicstraCell FINISHCELL = null;
//�������� ������ �� ��������� � �������� ������
CustomCell startCell = getCell(startPoint.x, startPoint.y);
CustomCell finishCell = getCell(finishPoint.x, finishPoint.y);
//���������� �������� ������� ������ ����� ��������� �� ��������
// ������ DeicstraCell
for(CustomCell TEMPCELL : area){
DeicstraCell tempDC = new DeicstraCell(TEMPCELL);
if (TEMPCELL == startCell) STARTCELL = tempDC;
if (TEMPCELL == finishCell) FINISHCELL = tempDC;
tempArea.add(tempDC);
}
//��������� ����� ������ �� ������� ����� ������ � ����������
ArrayList retAL = new ArrayList();
//��� ��������� ����� ������ ��������������� � ����
STARTCELL.setPredecessor(STARTCELL);
//��������� ���� ��������� ����� ����� �� ���������
STARTCELL.setPassedWay(STARTCELL.getCoast());
//��������� � �������� ����� ������ ����� ������ ������������
STARTCELL.setStatus(STARTCELL.STATUS_NOVISITED);
FINISHCELL.setStatus(FINISHCELL.STATUS_NOVISITED);

//�������� ��������� ����� ��� ���������
STARTCELL.setStatus(STARTCELL.STATUS_BARRIER);
//������� ������ ��������� �����
BarrierList.clear();
//�������� � ���� ��������� �����
BarrierList.add(STARTCELL);
DeicstraCell minCC = null;
DeicstraCell tempCC = null;

while(!BarrierList.isEmpty()){
//����� ���� ��������� ����� ������� ������1 - ������ � ����������� ������ ������ 
minCC = findOfBorderList(BarrierList);
//���� ������� ����� �� ������� - ���� ���� �� �����
if(minCC==null)
	return null;
/*if (minCC == null) {
NoWayException e = new NoWayException("Way can not be fined!");
throw e;
}*/
//��� ��������� ������ � ����������� ������ ������ ������������� �������
ArrayList <DeicstraCell> tempAL = getCleanEnvironment(minCC, tempArea);
//���� ������� ���
if ((tempAL ==null)||(tempAL.isEmpty())) {
//�������� ��������� ������ ��� �����������
minCC.setStatus(minCC.STATUS_OFFCAST);
//� ������� �� �� ������ ��������� �����
BarrierList.remove(minCC);
//������� � ������ ������ ����� � ����������� ������ ������
//�� ������ ��������� �����
continue;
}
//���� ����� ����� ������ �������������,
//� ��� ������ ������������ ������� getCleanEnvironment()
//�������� � ���������, �� ��� ������� �� ���
for ( DeicstraCell dCell : tempAL){
//�� �� ����������� ��� (������) ��� ��������� ������,
dCell.setStatus(dCell.STATUS_BARRIER);
//��������� � ������ ��������� �����
BarrierList.add(dCell);
//� ��������� ������1 ��� ���������� ��� ����.
dCell.setPredecessor(minCC);
//��������� ������� ��� ����� ��������������� � ��������� ������ ������
dCell.setPassedWay(minCC.getPassedWay() + dCell.getCoast());
//������� ���������� ���� ��� ����� ������� �� ������
dCell.setResiduaryWay( Math.sqrt((double)
(dCell.getX() - finishCell.getX())*(dCell.getX() - finishCell.getX())
+ (dCell.getY() - finishCell.getY())*(dCell.getY() - finishCell.getY())));
//���� ����� - ��� �����, �� ���� ������
if (dCell.equals(FINISHCELL)) {
//System.out.println("WayFinded");
//����������� ����
while (!minCC.getPredecessor().equals(STARTCELL)){
retAL.add(minCC.getParentCell().getPosition());
minCC = minCC.getPredecessor(); 
}
retAL.add(minCC.getParentCell().getPosition());
return retAL;
}
}
//��������������� ������ �������� ��� �����������.
minCC.setStatus(minCC.STATUS_OFFCAST);
//������� �� ������ ��������� �����
BarrierList.remove(minCC);
}
//���� ��������� ����� ������ ��� - �� ���� �� ������
// if (BarrierList.isEmpty()) System.err.println("No Way!");
//NoWayException e = new NoWayException("Way can not be fined!");
//throw e;
return null;
}

/** ����� ������� ������ � ����������� ������ ������
* ����� ���� ��������� ����� �� ������
* @param BarrierList - ������ ��������� �����
*/
private DeicstraCell findOfBorderList(ArrayList <DeicstraCell> BarrierList){
if (BarrierList.isEmpty()) return null;
Iterator tempI = BarrierList.iterator();
DeicstraCell tempCC, minCC = BarrierList.get(0);
double minSO = minCC.getSummaryWay();
double tempSO = 0.0;
while (tempI.hasNext()){
tempCC = (DeicstraCell)tempI.next();
tempSO = tempCC.getSummaryWay();
if (tempSO < minSO){
minSO = tempSO;
minCC = tempCC;
}
}
return minCC;
}
/** ����� ������� ������������ ���� �������� ������� findWay
* ����������� ��������� �� ���� ���� ��� ��� ��������
* @param way - ����, ������������������ ����� ������ Point
*/
public ArrayList optimizeWay(ArrayList <Point> way){
if (way == null) return null;
if (way.size()<3) return way;
Point p1,p2,p3;
ArrayList <Point> retWay = new ArrayList <Point> ();
for (int i=0; i<way.size()-3; i++){
p1 = way.get(i);
p2 = way.get(i+1);
p3 = way.get(i+2);

// p2 p3
// p1 
if ((p1.x == p2.x)&&(p2.y == p3.y)&&(p1.y == p2.y+1)&&(p2.x == p3.x-1)){
if (getCell(p3.x,p1.y).isPassable()){
way.remove(i+1);
continue;
}

}
// p2 p1
// p3 
if ((p3.x == p2.x)&&(p2.y == p1.y)&&(p3.y == p2.y+1)&&(p2.x == p1.x-1)){
if (getCell(p1.x,p3.y).isPassable()){
way.remove(i+1);
continue;
}

}
// p1 p2
// p3 
if ((p1.y == p2.y)&&(p2.x == p3.x)&&(p1.y == p3.y-1)&&(p1.x == p2.x-1)){
if (getCell(p1.x,p3.y).isPassable()){
way.remove(i+1);
continue;
}

}
// p3 p2
// p1 
if ((p3.y == p2.y)&&(p2.x == p1.x)&&(p3.y == p1.y-1)&&(p3.x == p2.x-1)){
if (getCell(p3.x,p1.y).isPassable()){
way.remove(i+1);
continue;
}

}
// p1
// p3 p2
if ((p1.x == p2.x)&&(p2.y == p3.y)&&(p1.y == p3.y-1)&&(p1.x == p3.x+1)){
if (getCell(p3.x,p1.y).isPassable()){
way.remove(i+1);
continue;
}

}
// p3
// p1 p2
if ((p3.x == p2.x)&&(p2.y == p1.y)&&(p3.y == p1.y-1)&&(p3.x == p1.x+1)){
if (getCell(p1.x,p3.y).isPassable()){
way.remove(i+1);
continue;
}

}
// p3
// p2 p1
if ((p1.y == p2.y)&&(p2.x == p3.x)&&(p1.y == p3.y+1)&&(p1.x == p2.x+1)){
if (getCell(p1.x,p3.y).isPassable()){
way.remove(i+1);
continue;
}

}
// p1
// p2 p3
if ((p3.y == p2.y)&&(p2.x == p1.x)&&(p3.y == p1.y+1)&&(p3.x == p2.x+1)){
if (getCell(p3.x,p1.y).isPassable()){
way.remove(i+1);
continue;
}

}
} 
return way;
}

/** ����� ���������� ��� ������ �� ����������
* � �������� ��������� ���������
*
*/ 
public void paint(Graphics g){
for (CustomCell tempCC : this.area){
tempCC.paint(g);
}
}

//������ ���������
public final int ENVIROMENT_MODEL_SNOWFLAKE = 1;
public final int ENVIROMENT_MODEL_CROSS = 2;

//���������� ����� �����
private ArrayList <CustomCell> area = new ArrayList <CustomCell> ();

//������ � ������ ������
private int width = 1;
private int heigth = 1;
}