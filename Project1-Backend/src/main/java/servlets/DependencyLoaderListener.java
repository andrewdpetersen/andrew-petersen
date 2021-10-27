package servlets;

import models.Flights;
import models.People;
import models.Tickets_People_Flights;
import org.hibernate.cfg.Configuration;
import services.HibernateManagement;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DependencyLoaderListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Configuration config = new Configuration();
        config.addAnnotatedClass(People.class);
        config.addAnnotatedClass(Flights.class);
        config.addAnnotatedClass(Tickets_People_Flights.class);

        HibernateManagement.setSessionFactory(config.buildSessionFactory());
        HibernateManagement.setSession(HibernateManagement.getSessionFactory().openSession());
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        HibernateManagement.getSession().close();
    }
}
