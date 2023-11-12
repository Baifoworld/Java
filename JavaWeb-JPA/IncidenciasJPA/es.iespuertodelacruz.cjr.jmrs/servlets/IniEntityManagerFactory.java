package servlets;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class IniEntityManagerFactory
 *
 */
@WebListener
public class IniEntityManagerFactory implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public IniEntityManagerFactory() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("unidadPersistencia");
        sce.getServletContext().setAttribute("emf", emf);
    }
	
}
