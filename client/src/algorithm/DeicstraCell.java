package algorithm;

/*
* DeicstraCell.java
*
* Created on 20 ������ 2007 �., 0:03
*
*/

/**
*
* @author 222
*/
import java.awt.*;

public class DeicstraCell {

/** Creates a new instance of DeicstraCell */
public DeicstraCell(CustomCell ParentCell) {
this.ParentCell = ParentCell;
if (ParentCell.isPassable()){
this.setStatus(this.STATUS_NOVISITED);
//System.out.println(ParentCell.toString());
} else this.setStatus(this.STATUS_BORDER);
}

public CustomCell getParentCell(){
return this.ParentCell;
}
public double getCoast(){
return this.getParentCell().Cost;
}
//������ ��������������, �.�. �� �� ������� ������
private DeicstraCell Predecessor;
public void setPredecessor (DeicstraCell Predecessor){
this.Predecessor = Predecessor;
}
public DeicstraCell getPredecessor() {return this.Predecessor;}

//��������� ������� ������ 
final int STATUS_NOVISITED=1; //������������
final int STATUS_BORDER=2; //
final int STATUS_OFFCAST=3; //�����������
final int STATUS_BARRIER=4; //���������

//������ ������
private int Status = this.STATUS_NOVISITED;
public void setStatus(int Status){
this.Status = Status;
}
public int getStatus() {return this.Status;}

//���������� ����
private double PassedWay =0;
public void setPassedWay(double PassedWay){
this.PassedWay = PassedWay;
}
public double getPassedWay() {return this.PassedWay;}

//���������� ����
private double ResiduaryWay = 0;
public void setResiduaryWay(double ResiduaryWay){
this.ResiduaryWay = ResiduaryWay;
}
public double getResiduaryWay() {return this.ResiduaryWay;}

//�������� ����
public double getSummaryWay() {
return this.getPassedWay() + getResiduaryWay();
} 

//��������� ������
private Point Position = new Point();
public Point getPosition(){
return this.ParentCell.getPosition();
}

public int getX(){
return this.getPosition().x;
}
public int getY(){
return this.getPosition().y;
} 

public String toString(){
return ("("+ getX() + ";" + getY() +"):\n"
+ "Status: " + getStatus() + "\n"
+ "MaxWay:" + getSummaryWay());
}

private CustomCell ParentCell = null;
}
