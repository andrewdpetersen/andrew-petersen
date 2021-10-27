package services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class HibernateManagement {
    private static SessionFactory sessionFactory;
    private static Session session;

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void setSessionFactory(SessionFactory sessionFactory) {
        HibernateManagement.sessionFactory = sessionFactory;
    }

    public static Session getSession() {
        return session;
    }

    public static void setSession(Session session) {
        HibernateManagement.session = session;
    }
}
