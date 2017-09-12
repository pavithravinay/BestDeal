
import java.util.*;
import java.util.logging.*;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.ServletContextEvent;

@WebListener
public class BestDealLifeCycleListener implements ServletContextListener {	  

      public void contextInitialized(ServletContextEvent event) {
          SmartPhone Smartphone = new SmartPhone();
          Smartphone.buildBasicSmartPhoneList();
      }

      public void contextDestroyed(ServletContextEvent event) {
      	IMySqlDataStoreUtilities dataUtil = new MySqlDataStoreUtilities();       
		Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.INFO, "before resetting");
        dataUtil.resetProducts();
      }
}