package es.palma.provajsp1.listeners;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import es.palma.provajsp1.entities.Company;
import es.palma.provajsp1.entities.UserBean;

/**
 * Application Lifecycle Listener implementation class IniciAplicacio1
 *
 */
@WebListener
public class IniciAplicacio implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public IniciAplicacio() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
    	System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Contexte finalitzat");
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Contexte inicialitzat");
       
    }
	
}
