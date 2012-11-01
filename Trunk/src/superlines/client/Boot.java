package superlines.client;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.SwingUtilities;
import org.apache.commons.logging.*;
import org.apache.log4j.PropertyConfigurator;
import superlines.client.ui.LoginDialog;
import superlines.client.ui.MainFrame;
import superlines.core.SuperlinesController;
import superlines.core.SuperlinesControllerImpl;



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
             //   final SuperlinesController ctr = new SuperlinesControllerImpl(ul);
                
                final LoginDialog loginFrame = new LoginDialog(frame, true);    
                loginFrame.setVisible(true);
                

     
            }
            
            });
            
            
    	log.debug("application terminate");
    }
    

}