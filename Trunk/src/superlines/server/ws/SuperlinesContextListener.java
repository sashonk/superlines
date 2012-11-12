package superlines.server.ws;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import superlines.server.PromotionHelper;

public class SuperlinesContextListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		
		  ServletContext servletCtx = event.getServletContext();
		  String webContentFolder = servletCtx.getRealPath("/");
		  System.setProperty("config.file.path", String.format("%s/%s", webContentFolder, "WEB-INF/config/config.properties"));
		  System.setProperty("data.folder", String.format("%s/%s", webContentFolder, "WEB-INF/data"));
		  
		  PromotionHelper.fillData();
	}	

}
