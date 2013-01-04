import java.awt.*;


import javax.imageio.ImageIO;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.io.IOException;

import settings.AppData;
import settings.DES;
import settings.UserSettings;
import settings.client;

import algorithm.DeicstraArea;
import algorithm.NoWayException;

import dialog.LoginDlg;
import dialog.MyButton;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//MyFrame mf = new MyFrame();
		File fd = new File("data");
		if(!fd.exists()) 
				fd.mkdir();
		
		new MyFrame();
		
	}
}

class ImageHolder {
	private static BufferedImage back;
	public static void setBack(BufferedImage img)
	{
		back = img;
	}
	public static BufferedImage getBack()
	{
		return back;
	}
	
	static{
		try{
		back = ImageIO.read(new File("back.jpg"));
		}
		catch(Exception e){
			
		}
	}
}

final class BackPane extends JPanel{
	public void paint(Graphics g)
	{
		super.paint(g);
		g.drawImage(ImageHolder.getBack(), 0, 0, this.getWidth(), this.getHeight(), null);
	}
}

final class MyFrame extends JFrame
{	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MyFrame frame;
	 


	 //Rectangle bounds;
	 public MyFrame()
	{		 

		 
		 this.online = AppData.getInstance().IsOnline();
		 if(online){
			 boolean updated = AppData.getInstance().Updated();
			 if(!updated){
				 JOptionPane.showMessageDialog(null, "Для игры доступны обновления", "Сообщение", JOptionPane.INFORMATION_MESSAGE);
			 }
		 }
		 
		 setTitle("Lines");
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		frame = this;
		Dimension sz = Toolkit.getDefaultToolkit().getScreenSize();
		this.setResizable(false);
		this.setBackground(Color.LIGHT_GRAY);
		fulscr = AppData.getInstance().getFullScreen();
		int fs =  fulscr ? 0 : 1;
		this.setBounds(new Rectangle(fs*200, fs*200, sz.width-fs*400, sz.height-fs*300));
		 f = fulscr ? new Font("Times New Roman", Font.BOLD, 24) : new Font("Times New Roman", Font.BOLD, 12);
		defBtnW = getWidth()/12;
			
		 model = new MyTableModel();

		goMenu();
		goPlay();
		goStats();
		goMenu();
		
		BackPane backPane = new BackPane();
		backPane.setBounds(0, 0 , this.getWidth(), this.getHeight());
		add(backPane);
		
		this.setVisible(true);
		//Font mainLabFont = new Font("Times New Roman", Font.BOLD, 140);
		
		// BackPane backPane = new BackPane();
		 

		//goIntro();
	}
	 
	 private void goMenu()
	 {
		 
		 //this.removeAll();
		 this.setLayout(null);	
		 hideAll();
		 if(menuItems.size()>0)
		 {
			 show(menuItems);
			 return;
		 } 
		 
		 		 
		 final int dlgW = getWidth()/3;
		 final int dlgH = getHeight()/3;
		final LoginDlg dlg = new LoginDlg(dlgW, dlgH);
		//dlg.setBounds(getWidth()/2-dlgW/2, getHeight()/2-dlgH/2, dlgW, dlgH);
		dlg.setLocation(getWidth()/2-dlgW/2+getX(), getHeight()/2-dlgH/2 + getY());
		dlg.setVisible(true);
		
		 int pad_top = this.getBounds().height/4;
		 int margin = this.getBounds().height/16;
		 int btn_width = this.getBounds().width/7;
		 int btn_height = this.getBounds().height/10;
		 int btnx = this.getBounds().width/2-btn_width/2;
		 int btny = pad_top;
			 	 
		 MyButton newBtn = new MyButton("Игра");
		 add(newBtn);
		 menuItems.add(newBtn);
		 newBtn.setFont(f);
		 newBtn.setBounds(btnx, btny, btn_width, btn_height);
		 newBtn.addMouseListener(new MouseAdapter(){
			 public void mouseClicked(MouseEvent e)
			 {
				 //Runtime.getRuntime().exit(0);
				 //scatter();
				 goPlay();
			 }
		 });
		 
		 //btny += btn_height + margin;
		 final JButton confBtn = new JButton("Настройки");
		 //add(confBtn);
		// menuItems.add(confBtn);
		 confBtn.setFont(f);
		 //confBtn.setEnabled(false);
		 confBtn.setBounds(btnx, btny, btn_width, btn_height);
		 confBtn.addMouseListener(new MouseAdapter(){
			 public void mousePressed(MouseEvent e)
			 {		

				 goConfig();
			 }
		 });

	
		 btny += btn_height + margin;
		 MyButton statBtn = new MyButton("Рейтинг");
		 add(statBtn);
		 menuItems.add(statBtn);
		 statBtn.setFont(f);
		 statBtn.setBounds(btnx, btny, btn_width, btn_height);
		 statBtn.addMouseListener(new MouseAdapter(){
			 public void mouseClicked(MouseEvent e)
			 {
				 //Runtime.getRuntime().exit(0);
				 goStats();
			 }
		 });
		 
		 btny += btn_height + margin;	 
		 JButton exitBtn = new MyButton("Выход");
		 add(exitBtn);
		 menuItems.add(exitBtn);
		 exitBtn.setFont(f);
		 exitBtn.setBounds(btnx, btny, btn_width, btn_height);
		 exitBtn.addMouseListener(new MouseAdapter(){
			 public void mouseClicked(MouseEvent e)
			 {
				 model.Save();
				 Runtime.getRuntime().exit(0);
			 }
		 });
		 
		 username = AppData.getInstance().GetUserName();
		 
		 repaint();
		 statBtn.repaint();
	 }
	 
	 private void goPlay()
	 {
		// repaint();
 
		 //this.removeAll();
			this.setLayout(null);	
		 hideAll();
		 if(playItems.size()>0)
		 {
			 show(playItems);
			 return;
		 }
		 
		 nColor = AppData.getInstance().getColorCount();
		 N = AppData.getInstance().getAreaSize();
		 n = AppData.getInstance().getNewBallsCount();
		 baseWin = AppData.getInstance().getBaseWin();
		 extraWin = AppData.getInstance().getExtraWin();
		 Nwin = AppData.getInstance().getCountWin();
		 
		 nextColors = new int[n];		 
		 if(cells==null)
			 cells = new CellField[N][N];
		 
		 colors.add(Color.BLUE);
		 colors.add(Color.YELLOW);
		 colors.add(Color.RED);
		 colors.add(Color.CYAN);
		 colors.add(Color.GREEN);
		 //colors.add(Color.ORANGE);
		 //colors.add(Color.PINK);
		 colors.add(Color.MAGENTA);
		 colors.add(new Color(0, 100, 0));
		 colors.add(Color.WHITE);
		 
		 int cw = getWidth()/35;
		 int ch = getHeight()/30;
		 int marg = 10;
		 int tw = (cw+marg)*n;
		 int cx = getWidth()/2 - tw/2;
		 int cy = getHeight()/10;
		 for(int i = 0; i<n; i++)
		 {
			 CellField cf = new CellField(0, 0);
			 add(cf);
			 playItems.add(cf);
			 cf.setBounds(cx, cy, cw, ch);
			 colorCells.add(cf);
			 cx += cw + marg;
		 }
		 generateNextColors();
		 
		 JLabel player = new JLabel("Игрок:  "+username);
		 player.setFont(f);
		 add(player);
		 playItems.add(player);
		 player.setBounds(getWidth()/20, cy, defBtnW*2, defBtnW/2);
		 
		 
		 JLabel newColLab = new JLabel("Новые");
		 newColLab.setFont(f);
		 add(newColLab);
		 playItems.add(newColLab);
		 newColLab.setSize(defBtnW, defBtnW/2);
		 newColLab.setLocation(getWidth()/2 - tw/2-newColLab.getWidth(), cy);
		 
		 JLabel colLab = new JLabel("Цвета");
		 colLab.setFont(f);
		 add(colLab);
		 playItems.add(colLab);
		 colLab.setSize(defBtnW, defBtnW/2);
		 colLab.setLocation(getWidth()/2 + tw/2, cy);
		 
		 int margin_r = 10;
		 int margin_t = 5;
		 int width = getWidth()/25;
		 int height = getHeight()/20;
		 int totalW = N*(width+margin_r);
		 int totalH = N*(height+margin_t);
		 int startx = getWidth()/2 - totalW/2;
		 int starty = getHeight()/2 - totalH/2;
		 int x = startx;
		 int y = starty;
		 		 
		 JButton scat = new MyButton("Scatter!");
		 add(scat);
		 playItems.add(scat);
		 scat.setBounds(startx + defBtnW/3, starty +totalH + defBtnW/3, defBtnW, defBtnW/2);
		 scat.addMouseListener(new MouseAdapter(){
			 public void mouseClicked(MouseEvent e)
			 {		 
				 scatter();
/*				 Vector<Object> v = new Vector<Object>();
				 v.add(username);
				 v.add(score);
				 model.addRecord(v);*/
			 }
		 });
		 
		 JButton btn = new MyButton("В меню");
		 add(btn);
		 playItems.add(btn);
		 btn.setBounds(startx + defBtnW/3+2*defBtnW, starty +totalH + defBtnW/3, defBtnW, defBtnW/2);
		 btn.addMouseListener(new MouseAdapter(){
			 public void mouseClicked(MouseEvent e)
			 {
				 //printCells();
				 File s = online ? new File("data/" +username+".net") : new File("data/" +username+".txt");
				 Save(s);
				 goMenu();
			 }
		 });
		 
		 JButton startNewBtn = new MyButton("Новая игра");
		 add(startNewBtn);
		 playItems.add(startNewBtn);
		 startNewBtn.setBounds(startx + defBtnW/3 + 4*defBtnW, starty +totalH + defBtnW/3, defBtnW, defBtnW/2);
		 startNewBtn.addMouseListener(new MouseAdapter(){
			 public void mouseClicked(MouseEvent e)
			 {
				 File s = online ? new File("data/" +username+".net") : new File("data/" +username+".txt");
				 Save(s);
				 startNew();
			 }
		 });
		 
		 
		 JLabel scor = new JLabel("Очки:");
		 scor.setFont(f);
		 add(scor);
		 playItems.add(scor);
		 scor.setBounds(getWidth()/7*6-defBtnW, cy, defBtnW, defBtnW/2);	
		 add(scoreLab);
		 playItems.add(scoreLab);
		 scoreLab.setFont(f);
		 scoreLab.setBounds(getWidth()/7*6, cy, defBtnW, defBtnW/2);
		 
		 		 
		 for(int i = 0; i<N; i++)
		 {
			 for(int j = 0; j<N; j++)
			 {
				 final CellField cf = new CellField(i , j);
				 playItems.add(cf);
				 cf.addMouseListener(new MouseAdapter(){
					 public void mousePressed(MouseEvent e)
					 {
						 if(cf.isEmpty() && frame.state==STATE.SELECTION)
							 return;
						 if(cf.isEmpty() && frame.state==STATE.ROUTING)
						 {	 
							 endCell = cf;
							 route();
							 return;
						 }
						 if(!cf.isEmpty()){
							 if(startCell!=null)
								 //setCellSelected(startCell, false);
								 startCell.setSelected(false);
							 startCell = cf;
							 cf.setSelected(true);
							 frame.state = STATE.ROUTING;
						 }
					 }
				 });
				 add(cf);
				 cells[i][j] = cf;				 
				 cf.setBounds(x, y, width, height);
				 x+= width + margin_r;
			 }
			 y+= height + margin_t;
			 x = startx; 
		 }
		 
		 File ifile = online ? new File("data/"+username+".net") :  new File("data/"+username+".txt");
		 if(!ifile.exists())
		 {
			 scatter();
		 }
		 else{
			 Load(ifile);
		 }
		 //JOptionPane.showMessageDialog((Component)null, "Сегодня никакой игры", "Ошибка", JOptionPane.OK_OPTION);
		// repaint();

	 }
		 
	 private void goStats()
	 {
		// repaint();

		 //this.removeAll();
		 this.setLayout(null);
		 hideAll();
		 if(statItems.size()>0)
		 {
			 show(statItems);
			 return;
		 }


		 JTable t = new JTable(model);	
		 t.setFont(f);
		 t.setRowHeight(30);
		 //t.setRowSorter(trs);
		 t.setDefaultRenderer(Object.class, new NewRecordCellRenderer());
		 t.getColumn("№").setMaxWidth(35);
		 
		 
		 JScrollPane jsp = new JScrollPane(t);
		 add(jsp);
		 statItems.add(jsp);
		 int w = getWidth()/3*2;
		 int h = getHeight()/3*2;
		 jsp.setBounds(getWidth()/2-w/2, getHeight()/2-h/2, w, h);
		 //t.setEnabled(false);
		 
		 JLabel user = new JLabel("Рейтинговая таблица");
		 user.setFont(f);
		 add(user);
		 statItems.add(user);
		 user.setBounds(getWidth()/2 - defBtnW/2-50, getHeight()/20, (int)(defBtnW*2.5), defBtnW/2);
		 
		 JButton leave = new MyButton("Назад");
		 add(BorderLayout.SOUTH, leave);
		 statItems.add(leave);
		 leave.addMouseListener(new MouseAdapter(){
			 public void mouseClicked(MouseEvent e)
			 {
				 goMenu();
			 }
		 });
		 leave.setBounds(getWidth()*17/20, getHeight()*17/20, defBtnW, defBtnW/2);
		 leave.setFont(f);
		 
		 repaint();
	 }
	 
	 private void goConfig()
	 {
		 //this.removeAll();
		// repaint();

		this.setLayout(null);	
		 hideAll();
		 if(confItems.size()>0)
		 {
			 show(confItems);
			 return;
		 }
		 
		 int W = getWidth();
		 int H = getHeight();
		 int pw = 200;
		 int ph = 200;
		 JButton backBtn = new MyButton("Назад");
		 add(backBtn);
		 backBtn.setFont(f);
		 confItems.add(backBtn);
		 backBtn.setBounds(getWidth()*17/20, getHeight()*17/20, defBtnW, defBtnW/2);
		 backBtn.addMouseListener(new MouseAdapter(){
			 public void mouseClicked(MouseEvent e)
			 {
				 goMenu();
			 }
		 });
		 
		 JLabel lab = new JLabel("Настройки");
		 add(lab);
		 lab.setFont(f);
		 confItems.add(lab);
		 lab.setBounds(getWidth()/2 - defBtnW/2, getHeight()/20, defBtnW*2, defBtnW/2);

		 JPanel jpan = new JPanel();
		 add(jpan);
		 confItems.add(jpan);
		 jpan.setBounds(W/2-pw/2, H/2-ph/2, pw, ph);
		 jpan.setBackground(Color.GRAY);
		 jpan.setLayout(new FlowLayout());
		 
		 UserSettings us = AppData.getInstance().getUserSettings(username);
		 JCheckBox tip = new JCheckBox("Подсказка", us.getTip());
		 
		 //confItems.add(tip);
		 //tip.setBounds(W/2-defBtnW/2, H/2-defBtnW/2, defBtnW, defBtnW/2);
		 
		 
		 jpan.add(tip);
		 jpan.add(new JLabel("dasda"));
		 jpan.add(new JLabel("dasda"));
		 jpan.add(new JLabel("dasda"));
		 
		 repaint();
	 }
	 
	 /** поиск маршрута для шарика*/
	 private boolean route()
	 {
		 updateArea();

		 if(startCell==null || endCell==null || state==STATE.SELECTION)
				JOptionPane.showMessageDialog(null, "Внутренняя ошибка", "Error", JOptionPane.ERROR_MESSAGE);			 
		 DeicstraArea da = DeicstraArea.getInstance();
		 List route = null;
		  try {
			 // JOptionPane.showMessageDialog(null, startCell.GetP().toString());
			route = da.findWay(startCell.GetP(), endCell.GetP());
			
			 //JOptionPane.showMessageDialog(null, route.toString());
		} catch (NoWayException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);		}
				
		
		if(route!=null)
		{		
/*			for(int i=0;i<route.size(); i++) //UNCOMMENT TO SEE ROUTE
			{
				Point p = (Point)route.get(i);
				cells[p.x][p.y].setSelected(true);
			}*/
			
			endCell.setColor(startCell.getColor());
			startCell.setEmpty(true);
			frame.state = STATE.SELECTION;
			if(!checkWin(endCell))
				scatter();
			
			return true;
		}
		else{
			return false;
		}
	 }
	 
	 private void generateNextColors()
	 {
		 Random rnd = new Random(System.nanoTime());
		 for(int i =0; i<nextColors.length; i++)
		 {
			 nextColors[i] = rnd.nextInt(nColor);
			 colorCells.get(i).setColor(colors.get(nextColors[i]));
		 }
		 
	 }
	 /**выброс порции новых шариков	*/
	 private void scatter()
	 {
		 List<CellField> emptyCells = new ArrayList<CellField>();
		 List<CellField> checkCells = new ArrayList<CellField>();
		 for(int i=0;i<N;i++)
		 {
			 for(int j=0;j<N;j++)
			 {
				 if(cells[i][j].isEmpty())
					 emptyCells.add(cells[i][j]);
			 }
		 }
		 for(int k = 0; k<n ;k++){
			 //no more cells left
			 if(emptyCells.size()==0)
			 {
				 endPlay();
				 return;
			 }
			 Random r = new Random(System.nanoTime());
			 int v = r.nextInt(emptyCells.size());
			 CellField cf = emptyCells.get(v);
			 //int c = r.nextInt(nColor);
			 cells[cf.GetP().x][cf.GetP().y].setColor(colors.get(nextColors[k]));
			 checkCells.add(cf);
			 emptyCells.remove(v);
			 //nextColors[k] = r.nextInt(nColor);
		 }

		 generateNextColors();
		 for(int i = 0; i<checkCells.size(); i++)
		 {
			 checkWin(checkCells.get(i));
		 }

		 if(emptyCells.size()==0)
		 {
			 endPlay();
			 return;
		 }
		 //updateArea();
	 }
	 
	 /**проверка наличия выигрышных комбинаций*/
	 private boolean checkWin(CellField cf)
	 {
		 List<CellField> winCells = new ArrayList<CellField>();
		 List<CellField> temp = new ArrayList<CellField>();

		 Point p = cf.GetP();
		 int i = p.x;
		 int j = p.y;
		 Color c = cf.getColor();
		 while(i>0)
		 {
			 i--;
			 if(cells[i][p.y].getColor().equals(c))
			 {
				 temp.add(cells[i][p.y]);
			 }
			 else break;
		 }
		 i = p.x;
		 while(i<N)
		 {
			 if(cells[i][p.y].getColor().equals(c))
			 {
				 temp.add(cells[i][p.y]);
			 }
			 else break;
			 i++;
		 }
		 i = p.x;
		 if(temp.size()>=Nwin)
		 {
			 winCells.addAll(temp);
			 //за каждый шарик сверх нормы, добавляется extra очков к выигрышу 
			 score += baseWin + (temp.size()-Nwin)*extraWin;
		 }
		 temp.clear();
		 
		 while(j>0)
		 {
			 j--;
			 if(cells[p.x][j].getColor().equals(c))
			 {
				 temp.add(cells[p.x][j]);
			 } 
			 else break;
		 }
		 j = p.y;
		 while(j<N)
		 {
			 if(cells[p.x][j].getColor().equals(c))
			 {
				 temp.add(cells[p.x][j]);
			 } 
			 else break;
			 j++;
		 }
	
		 if(temp.size()>=Nwin)
		 {
			 winCells.addAll(temp);
			 //за каждый шарик сверх нормы, добавляется extra очков к выигрышу 
			 score += baseWin + (temp.size()-Nwin)*extraWin;
		 }
		 temp.clear();
		 
		 ///ПРОВЕРЯЕМ КОМБИНАЦИИ ПО ДИАГОНАЛИ -----------------
		 i = p.x;
		 j = p.y;
		 while(i>0 && j>0)
		 {
			 i--;
			 j--;
			 if(cells[i][j].getColor().equals(c))
			 {
				 temp.add(cells[i][j]);
			 }
			 else break;
		 }
		 
		 i= p.x;
		 j = p.y;
		 while(i<N && j<N)
		 {
			 if(cells[i][j].getColor().equals(c))
			 {
				 temp.add(cells[i][j]);
			 }
			 else break;
			 i++;
			 j++;
		 }
		 
		 if(temp.size()>=Nwin)
		 {
			 winCells.addAll(temp);
			 //за каждый шарик сверх нормы, добавляется extra очков к выигрышу 
			 score += baseWin + (temp.size()-Nwin)*extraWin;
		 }
		 temp.clear();
		 
		 
		 i = p.x;
		 j = p.y;
		 while(i>0 && j<N-1)
		 {
			 i--;
			 j++;
			 if(cells[i][j].getColor().equals(c))
			 {
				 temp.add(cells[i][j]);
			 }
			 else break;
		 }
		 
		 i= p.x;
		 j = p.y;
		 while(i<N && j>=0)
		 {
			 if(cells[i][j].getColor().equals(c))
			 {
				 temp.add(cells[i][j]);
			 }
			 else break;
			 i++;
			 j--;
		 }
		 
		 if(temp.size()>=Nwin)
		 {
			 winCells.addAll(temp);
			 //за каждый шарик сверх нормы, добавляется extra очков к выигрышу 
			 score += baseWin + (temp.size()-Nwin)*extraWin;
		 }
		 temp.clear();
		 
		 if(winCells.size()>0){
			 for(i = 0; i<winCells.size(); i++)
			 {
				 CellField f = winCells.get(i);
				 cells[f.GetP().x][f.GetP().y].setEmpty(true);

			 }
			 //JOptionPane.showMessageDialog(null, "Win!");
			 scoreLab.setText(Integer.toString(score));
			 return true;
		 }
		 
		 return false;
	 }
	 
	 /** не осталось свободных ячеек, игра закончена*/
	 private void endPlay()
	 {
		 JOptionPane.showMessageDialog(null, "Игра закончена. Очки: "+score);
		 Vector<Object> v = new Vector<Object>();
		 v.add(0);
		 v.add(username);
		 v.add(score);
		 v.add(1);
		 model.addRecord(v);	 
		 //cli.sendResults(username,score);
		 if(online)
		 {
			 client c = null;
			 try {
				c = new client();
				c.sendResults(username, score);
				c.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Невозможно установить соединение с сервером. \n Результат будет отправлен позднее");
				String s = username+" " + score;
				try {
					FileOutputStream ifstr = new FileOutputStream("data/defer/"+username+"_defer.net");
					ifstr.write(DES.encrypt(s.getBytes()));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		 }
		 

		 startNew();
		 File s = online ? new File("data/" +username+".net") : new File("data/" +username+".txt");
		 Save(s);
		 goStats();		 
	 }
	 
	 /**начать новую игру */
	 private void startNew()
	 {
		 for(int i =0;i<N; i++)
		 {
			 for(int j =0;j<N; j++)
			 {
				 cells[i][j].setEmpty(true);
			 }
		 }
		 score = 0;
		 scoreLab.setText("0");
		 scatter();
	 }
	 
	 private void show(List<Component> list)
	 {
		for(int i =0; i<list.size(); i++)
		{
			list.get(i).setVisible(true);
		}
	 }
	 
	 private void hideAll()
	 {
		 for(int i = 0; i<menuItems.size(); i++)
		 {
			 menuItems.get(i).setVisible(false);
		 }
		 for(int i = 0; i<playItems.size(); i++)
		 {
			 playItems.get(i).setVisible(false);
		 }
		 for(int i = 0; i<statItems.size(); i++)
		 {
			 statItems.get(i).setVisible(false);
		 }
		 for(int i = 0; i<confItems.size(); i++)
		 {
			 confItems.get(i).setVisible(false);
		 }
	

	 }
	 
	 
	 private void updateArea()
	 {
		int[][] area = new int[N][N];
		for(int i =0; i<N; i++)
		{
			for(int j = 0; j<N; j++)
			{
				if(!cells[i][j].isEmpty()){
					area[i][j] = -1;
				}
				else{
					area[i][j] = 1;
				}
			}
		}
		DeicstraArea.getInstance().makeArea(area);
		Runtime.getRuntime().gc();
	 }
	 
	 private void Save(File ofile)
	 {
		 try {
			ofile.createNewFile();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		 FileOutputStream ofstream = null;
		 try {
			 ofstream = new FileOutputStream(ofile);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e1.toString(), "Error", JOptionPane.ERROR_MESSAGE);		}
		
		String s = "";
		for(int i=0;i<nextColors.length; i++)
		{
			s += nextColors[i]+"/";
		}
		s+=score +"/";
		for(int i =0; i<N; i++)
		{
			for(int j =0; j<N; j++)
			{
				s+=getCellColorIndex(cells[i][j]) + "/";
			}
		}
		try {
			
			if(online)
				ofstream.write(DES.encrypt(s.getBytes()));
			else
				ofstream.write(s.getBytes());

			ofstream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);		}	
	 }
	 
	 private void Load(File ifile)
	 {
		 FileInputStream ifstream = null;
		 try {
			 ifstream = new FileInputStream(ifile);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e1.toString(), "Error", JOptionPane.ERROR_MESSAGE);		}
		try {
		byte[] buf = new byte[ifstream.available()];
		ifstream.read(buf);
		if(online)
			buf= DES.decrypt(buf);
		String s = new String(buf);
		String[] ss = s.split("/");
		int i = 0;
		for(i =0; i<n; i++)
		{
			nextColors[i] = Integer.parseInt(ss[i]);
			colorCells.get(i).setColor(colors.get(nextColors[i]));
		}
		score = Integer.parseInt(ss[i++]);
		scoreLab.setText(Integer.toString(score));
		for(int x =  0;x<N;x++)
		{
			for(int y = 0;y<N;y++)
			{
				int index = Integer.parseInt(ss[i++]);
				if(index==-1)
					cells[x][y].setEmpty(true);
				else
				cells[x][y].setColor(colors.get(index));
			}
		}
		
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e1.toString(), "Error", JOptionPane.ERROR_MESSAGE);;
		}
	 }
	 
	 private int getCellColorIndex(CellField cf)
	 {
		 for(int i=0; i<nColor; i++)
		 {
			 if(cf.getColor().equals(colors.get(i)))
				 return i;
		 }
		 //JOptionPane.showMessageDialog(null, "Ошибка определения цветового индекса");
		 return -1;
	 }
	 
	 
	 
	 private List<Component> menuItems = new ArrayList<Component>();
	 private List<Component> playItems = new ArrayList<Component>();
	 private List<Component> statItems = new ArrayList<Component>();
	 private List<Component> confItems = new ArrayList<Component>();
	 private final JLabel scoreLab = new JLabel("0");
	 private final List<CellField> colorCells = new ArrayList<CellField>();
	 private  int n; //Кол-во новых шариков
	 private  int nColor; //Кол-во цветов сумм.
	 private  int N ; //размер игрового поля
	 private  int Nwin;
	 private int score ;
	 private  int baseWin ;
	 private  int extraWin;
	 private CellField[][] cells = null;
	 private CellField startCell = null;
	 private CellField endCell = null;
	 private List<Color> colors = new ArrayList<Color>();
	 private boolean fulscr;
	 private int[] nextColors;
	 private MyTableModel model = null;
	 private enum STATE{
		 SELECTION,
		 ROUTING	 
	 };
	 private Font f = null;
	 private STATE state = STATE.SELECTION;
	 private int defBtnW;
	 private String username;
	 private boolean online;
	// private BufferedImage image = null;
	 //private client cli = new client();
	 
	 //private List<Component> menuItems = new ArrayList<Component>();
	 
}


