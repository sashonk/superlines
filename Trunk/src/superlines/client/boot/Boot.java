package superlines.client.boot;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;


import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.SwingUtilities;

import org.apache.commons.logging.*;
import org.apache.log4j.PropertyConfigurator;


import superlines.client.Messages;
import superlines.client.Persister;
import superlines.client.ProfileController;
import superlines.client.ProfileControllerImpl;
import superlines.client.RateController;
import superlines.client.RateControllerImpl;
import superlines.client.RatePanelModel;
import superlines.client.ScoreSender;
import superlines.client.SuperlinesController;
import superlines.client.SuperlinesControllerImpl;
import superlines.client.SuperlinesOfflineController;
import superlines.client.ui.LoginDialog;
import superlines.client.ui.MainFrame;
import superlines.client.ui.PlayPanel;
import superlines.client.ui.RatePanel;
import superlines.client.ws.ServiceAdapter;
import superlines.core.Authentication;
import superlines.core.Configuration;
import superlines.core.Rank;
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
                final RatePanel scorePanel = new RatePanel();
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
                auth.setLocale(Configuration.get().getProperty("locale"));
                
                if(auth.getLogin()==null || auth.getPassword()==null){
                    return;
                }
                d.setErrorMessage("");
                
                ServiceAdapter adapter = ServiceAdapter.get();                
                if(adapter==null){
                    d.setErrorMessage(Messages.SERVICE_UNAVAILABLE.toString());
                    return;
                }
                
                   
                Profile profile = adapter.getProfile(auth);
                if(profile==null){                    
                    d.setErrorMessage(Messages.AUTH_FAILED.toString());
                    return;
                }
                
                
                ProfileControllerImpl profileCtr = new ProfileControllerImpl();
                profileCtr.setAuth(auth);
                profileCtr.setModel(profile);
                profile.registerListener(playPanel);
                playPanel.setController(profileCtr);
                playPanel.init(profile);

                SuperlinesControllerImpl ctr = new SuperlinesControllerImpl();               
                ctr.setAuth(auth);
                ctr.start();

                                            
                SuperlinesContext c = ctr.getContext();
                c.registerListener(playPanel);
                c.registerListener(profileCtr);
                playPanel.setController(ctr);               
                playPanel.init(c);
                
                Persister persister = new Persister();
                c.registerListener(persister);
                persister.setAuth(auth);
                persister.init(c);                
                frame.registerListener(persister);
    
                
                ScoreSender sender = new ScoreSender();
                sender.setAuth(auth);
                c.registerListener(sender);
                sender.init(c);
                

                RatePanelModel scoreModel = new RatePanelModel();
                scoreModel.registerListener(scorePanel);
                RateControllerImpl scoreCtr = new RateControllerImpl();
                scoreCtr.setAuth(auth);
                scoreCtr.setModel(scoreModel);
                scorePanel.setController(scoreCtr);
                scoreCtr.update();                                
                d.dispose();
                
                frame.setVisible(true);
                frame.showPlayPanel();
                }
                catch(Exception ex){
                    log.error(ex);
                    System.exit(1);
                }
            }
        });
        
        
        loginFrame.getOfflineButton().addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Profile profile = new Profile();
				profile.setRate(0);
				profile.setCreateDate(new Date());
				profile.setRank(Rank.NEWBIE);
				profile.setUsername("Anonimous");
												
				playPanel.init(profile);
				
				SuperlinesOfflineController ctr = new SuperlinesOfflineController();
				ctr.start();
				playPanel.setController(ctr);
				
				SuperlinesContext ctx = ctr.getContext();
				ctx.registerListener(playPanel);
				playPanel.init(ctx);
				
                loginFrame.dispose();
                
                frame.setVisible(true);
                frame.showPlayPanel();			
			}
        	
        });
        
        loginFrame.setVisible(true);
        
            }
            } );
            
    }}

            
        
            
            


    


