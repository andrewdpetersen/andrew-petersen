package hibernate;

import models.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public class StoreData {
    public static void main(String[] args) {

        //Create typesafe ServiceRegistry object
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();

        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        Users user_test=new Users();
        user_test.setUsername("hibernateUser");
        user_test.setPassword("hibernateUser");
        user_test.setEmail("hibernate@user.hbm");
        user_test.setFirst_name("Hibernate");
        user_test.setLast_name("User");

        session.save(user_test);
        t.commit();
        System.out.println("successfully saved");
        factory.close();
        session.close();

    }
}
