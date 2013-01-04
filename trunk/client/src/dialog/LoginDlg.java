package dialog;
import java.awt.Color;
//import org.example.


import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import settings.AppData;
import settings.Digest;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.zip.Checksum;
import java.util.zip.CRC32;


public class LoginDlg extends JDialog{
	public LoginDlg(int dlgW, int dlgH)
	{
		AppData.getInstance();
		int W = dlgW/3;
		int w = dlgW/9;
		int h = dlgH/8;
		setLayout(null);
		setResizable(false);
		setModal(true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		//setVisible(true);
		setTitle("Lines");
		setSize(dlgW, dlgH);
		
		final JComboBox jcombo = new JComboBox(AppData.getInstance().getUserNames());
		//final JLabel choice = new JLabel("Выбор игрока");
		final ButtonGroup bg = new ButtonGroup();
		final JRadioButton choice = new JRadioButton("   Выбор игрока ",true);
		final JRadioButton newuser = new JRadioButton("    Новый игрок ");
		bg.add(choice);
		bg.add(newuser);

		final JLabel pswd1 = new JLabel("Пароль");
		final JLabel pswd2 = new JLabel("Пароль");
		final JPasswordField pass1 = new JPasswordField();
		final JPasswordField pass2 = new JPasswordField();
		final JTextField name = new JTextField();
		final JButton go = new JButton("Игра");
		final JButton exit = new JButton("Выйти");
		//final JCheckBox oldpl = new JCheckBox("as");
			
		add(jcombo);
		add(choice);
		add(pswd1);
		add(pswd2);
		add(newuser);
		add(pass1);
		add(pass2);
		add(go);
		add(exit);
		add(name);
		
		choice.setBounds(w, h, W, h/2);
		jcombo.setBounds(w, 2*h, W, h/4*3);
		jcombo.setBackground(Color.WHITE);
		 
	
		pswd1.setBounds(w, 3*h, W, h);
		pass1.setBounds(w, 4*h, W, h/4*3);
		//pass1.setEchoChar('*');
		newuser.setBounds(w+W+w, h, W, h/2);
		name.setBounds(w+W+w, 2*h, W, h/4*3);
		pswd2.setBounds(w+W+w, 3*h, W, h/2);
		pass2.setBounds(w+W+w, 4*h, W, h/4*3);
		
		go.setBounds(w+w, 55*h/10, w*2, w/3*2);
		exit.setBounds(w+W+w, 55*h/10, w*2, w/3*2);
		
		exit.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
				Runtime.getRuntime().exit(0);
			}
		});
		
		go.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
				//если активирована опция выбора уже зарегистрированного пользователя			
				if(choice.isSelected()){
					if(jcombo.getItemCount()==0)
						return;

					//ch.update(name.getBytes(), 0, name.getBytes().length);
					//String expected = AppData.getInstance().getUserPswdHash((String)jcombo.getSelectedItem());
					
					String passwd = new String(pass1.getPassword());
					String name = (String)jcombo.getSelectedItem();
					String passhash = Digest.getMD5(passwd);
					
					if(AppData.getInstance().IsOnline()){
						boolean check = AppData.getInstance().checkUser(name, passhash);
						
						if(check){
							AppData.getInstance().SetUserName((String)jcombo.getSelectedItem());
							dispose();
						}
						else{
							JOptionPane.showMessageDialog(null, "Аккаунт не существует или пароль неверный!");
						}
					}
					else{
						boolean check = AppData.getInstance().checkLocalUser(name, passhash);
						
						if(check){
							AppData.getInstance().SetUserName((String)jcombo.getSelectedItem());
							dispose();
						}
						else{
							JOptionPane.showMessageDialog(null, "пароль неверный!");
						}
					}
					

				}
				
				//если активирована опция создания нового пользователя
				if(newuser.isSelected())
				{			
					
					String actName  = "";
					if(name.getText().equals("")){
						JOptionPane.showMessageDialog(null, "Введите имя!");
						return;
					}
					
					String username = name.getText();
					String pass= new String(pass2.getPassword());
					
					
					
					if(AppData.getInstance().IsOnline()){
						if(pass.equals("") || pass==null){
							JOptionPane.showMessageDialog(null, "В online-режиме пароль обязателен!");
								return;
						}
						
						if(!AppData.getInstance().appendUser(name.getText(), Digest.getMD5(pass)))
						{
							JOptionPane.showMessageDialog(null, "Пользователь с таким именем уже существует");
							return;
						}
						
					}
		
					
					actName =	AppData.getInstance().addLocalUser(username, Digest.getMD5(pass));
					
					

					AppData.getInstance().SetUserName(actName);

					dispose();
				}		
			}
		});
		
	
			
	}
	//private int defItemW;
}
