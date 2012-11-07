package superlines.client.boot;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.SwingUtilities;

import org.apache.commons.logging.*;
import org.apache.log4j.PropertyConfigurator;


import superlines.client.Context;
import superlines.client.ScoreController;
import superlines.client.ScoreControllerImpl;
import superlines.client.ScorePanelModel;
import superlines.client.SuperlinesController;
import superlines.client.SuperlinesControllerImpl;
import superlines.client.ui.LoginDialog;
import superlines.client.ui.MainFrame;
import superlines.client.ui.PlayPanel;
import superlines.client.ui.ScorePanel;
import superlines.client.ws.ServiceAdapter;
import superlines.core.Authentication;
import superlines.core.RulesHelper;
import superlines.core.SuperlinesContext;
import superlines.core.Profile;




public class Boot {
	
	private static final String LOG_CONFIG_PATH = "config/client/log4j.properties";
	private static final String APPLICATION_CONFIG_PATH = "config/client/boot.properties";
	private static final String DATA_FOLDER = "data/client";
	
	private static final Log log = LogFactory.getLog(Boot.class);
    
    public static void main(String[] argc) throws Exception{
        	Thread.currentThread().setName("main-thread");    	
    		System.setProperty("config.file.path",APPLICATION_CONFIG_PATH);
    		System.setProperty("data.folder", DATA_FOLDER);    	    

    		PropertyConfigurator.configure(LOG_CONFIG_PATH);
    	
    		log.debug("application start");
    		
			        try {
            // Set cross-platform Java L&F (also called "Metal")
     			 for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
            UIManager.setLookAndFeel(info.getClassName());
            break;
        }
    }
    } 
    catch (Exception e) {
       log.error(e);
    }
        
            SwingUtilities.invokeAndWait(new Runnable(){

            @Override
            public void run() {
                
                final  MainFrame frame = MainFrame.get();
                final ScorePanel scorePanel = new ScorePanel();
                final PlayPanel playPanel = new PlayPanel();
                frame.add(scorePanel);
                frame.add(playPanel);
                
                final LoginDialog loginFrame = new LoginDialog(frame, true);   
                        loginFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
            	log.debug("application terminate");
                System.exit(0);
            }
        });
                        
                        

        
        loginFrame.getOkButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                
                try{
 
                LoginDialog d =loginFrame;                
                Authentication auth = new Authentication();
                d.writeData(auth);
                //d.dispose();
                
                if(auth.getLogin()==null || auth.getPassword()==null){
                    return;
                }
                d.setErrorMessage("");
                
                ServiceAdapter adapter = ServiceAdapter.get();                
                if(adapter==null){
                    d.setErrorMessage(superlines.core.Messages.SERVICE_UNAVAILABLE.toString());
                    return;
                }
                
                   
                Profile profile = adapter.getProfile(auth);
                if(profile==null){                    
                    d.setErrorMessage(superlines.core.Messages.AUTH_FAILED.toString());
                    return;
                }
                
                Context ctx = Context.get();
                ctx.setOffline(false);
                ctx.setUser(profile);
                ctx.setAuth(profile.getAuth());
                SuperlinesController ctr = new SuperlinesControllerImpl();
                ctr.restart();
                ctr.scatter();
                                            
                SuperlinesContext c = ctr.getContext();
                c.registerListener(playPanel);
                playPanel.setController(ctr);               
                playPanel.init();
                
                
                RulesHelper.populateNextolors(ctr.getContext());    
                frame.setVisible(true);
                frame.showPlayPanel();
                
                ScorePanelModel scoreModel = new ScorePanelModel();
                scoreModel.registerListener(scorePanel);
                ScoreController scoreCtr = new ScoreControllerImpl();
                scoreCtr.setModel(scoreModel);
                scorePanel.setController(scoreCtr);
                scoreCtr.update();
                
                d.dispose();
                }
                catch(Exception ex){
                    log.error(ex);
                    System.exit(1);
                }
            }
        });
        
        loginFrame.getOfflineButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
            	
            	try{

                Context ctx = Context.get();
                ctx.setOffline(true);
                
                loginFrame.dispose();
                frame.setVisible(true);
                frame.showPlayPanel();
            
            	}
            	catch(Exception e){
            		log.error(e);
            	}
            }
        });
                
                
                loginFrame.setVisible(true);
                

     
            }
            
            });
            
            

    }
    

}
