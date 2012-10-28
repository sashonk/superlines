package superlines.client;


import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import org.apache.commons.logging.*;
import org.apache.log4j.PropertyConfigurator;
import superlines.client.ui.LoginDialog;
import superlines.client.ui.LoginFrame;
import superlines.client.ui.MainFrame;
import superlines.client.ui.PlayPanel;
import superlines.client.ui.ScorePanel;


public class Boot {
	
	private static final String LOG_CONFIG_PATH = "config/client/log4j.properties";
	private static final String APPLICATION_CONFIG_PATH = "config/client/boot.properties";
	
	private static final Log log = LogFactory.getLog(Boot.class);
    
    public static void main(String[] argc) throws Exception{
        	Thread.currentThread().setName("main-thread");    	
    		System.setProperty("config.file.path",APPLICATION_CONFIG_PATH);
    	
    	
    		PropertyConfigurator.configure(LOG_CONFIG_PATH);
    	
    		log.debug("application start");
        
            SwingUtilities.invokeAndWait(new Runnable(){

            @Override
            public void run() {
                
                final  MainFrame frame = MainFrame.get();
                final LoginDialog loginFrame = new LoginDialog(frame, true);    
                      
                loginFrame.getOkButton().addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent ae) {
                                loginFrame.dispose();
                        
                                frame.setVisible(true);
                                frame.showPlayPanel();
                    }
                });
                
                loginFrame.getOfflineButton().addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent ae) {
                                loginFrame.dispose();
                        
                                frame.setVisible(true);
                                frame.showPlayPanel();
                    }
                });
                
                loginFrame.setVisible(true);
                

     
            }
            
            });
            
            
    	log.debug("application terminate");
    }
    

}
