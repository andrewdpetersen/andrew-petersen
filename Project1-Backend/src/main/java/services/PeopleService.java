package services;

import models.Flights;
import models.People;
import models.Tickets_People_Flights;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.LinkedList;
import java.util.List;

public class PeopleService {
    private static Session session = HibernateManagement.getSession();
    private static SessionFactory sessionFactory = HibernateManagement.getSessionFactory();

    /**
     * This method saves a People instance to our database
     * @param person
     */
    public static void saveNewPerson(People person){
        session.save(person);
    }

    /**
     * This method fetches a person from our database based on their people_id.
     * @param people_id
     * @return
     */
    public static People getPersonById(int people_id){
        return session.get(People.class, people_id);
    }

    /**
     * This method returns a list of all people in our database
     * @return
     */
    public static List<People> getAllPeople(){
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<People> query = builder.createQuery(People.class);
        Root<People> root = query.from(People.class);
        query.select(root);
        return session.createQuery(query).getResultList();
    }

    public static People getPersonByUsername(String username){
        System.out.println("DEBUG: method called");
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<People> query = builder.createQuery(People.class);
        Root<People> root = query.from(People.class);
        System.out.println("DEBUG: root set");
        query.select(root).where(builder.equal(root.get("username"),username));
        Query getPerson = session.createQuery(query);
        System.out.println("DEBUG: session creates query");
        List<People> userList = getPerson.getResultList();

        if(userList.isEmpty()){
            return null;
        }else{
            People activeUser = new People();
            activeUser = userList.get(0);
            return activeUser;
        }
    }

    public static Session getSession() {
        return session;
    }

    public static void setSession(Session session) {
        PeopleService.session = session;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void setSessionFactory(SessionFactory sessionFactory) {
        PeopleService.sessionFactory = sessionFactory;
    }
}
