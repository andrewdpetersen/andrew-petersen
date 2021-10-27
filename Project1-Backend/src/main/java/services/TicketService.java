package services;

import models.Flights;
import models.People;
import models.Tickets_People_Flights;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class TicketService {
    // we want 2 private fields Session factory, session
    private static SessionFactory sessionFactory = HibernateManagement.getSessionFactory();  // so we can get the session
    private static Session session = HibernateManagement.getSession(); // We will use this to update the database


    //User - buy a TICKET (buying multiple tickets runs this multiple times)
    public static void buyNewTicket (Tickets_People_Flights tpf){

        session.beginTransaction();
        session.save(tpf);
        session.getTransaction().commit();
    }


    //User - CANCEL a ticket // Admin will also use
    public static void cancelTicket (Tickets_People_Flights tpf){
        session.beginTransaction();
        session.delete(tpf);// Use the session to delete the Object tpf from database
        session.getTransaction().commit();
    }

    public static void cancelTicketByCustomerFlight(People customer, Flights flight){
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Tickets_People_Flights> query = builder.createQuery(Tickets_People_Flights.class);
        Root<Tickets_People_Flights> root = query.from(Tickets_People_Flights.class);
        query.select(root).where(builder.and(builder.equal(root.get("person"),customer),
                builder.equal(root.get("flight"),flight)));
        Query getUserTicketsOnFlight = session.createQuery(query);
        List<Tickets_People_Flights> cancelledTickets= getUserTicketsOnFlight.getResultList();
        for (Tickets_People_Flights ticket:cancelledTickets) {
            session.beginTransaction();
            session.delete(ticket);
            session.getTransaction().commit();
        }
    }

    public static Tickets_People_Flights getTicketByID(int ticket_id){
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Tickets_People_Flights> query = builder.createQuery(Tickets_People_Flights.class);
        Root<Tickets_People_Flights> root = query.from(Tickets_People_Flights.class);
        query.select(root).where(builder.equal(root.get("ticket_id"),ticket_id));
        Query getTicket = session.createQuery(query);
        return (Tickets_People_Flights) getTicket.getSingleResult();
    }

    public static List<Tickets_People_Flights> getTicketsByFlight(Flights flight){
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Tickets_People_Flights> query = builder.createQuery(Tickets_People_Flights.class);
        Root<Tickets_People_Flights> root = query.from(Tickets_People_Flights.class);
        query.select(root).where(builder.equal(root.get("flight"),flight));
        Query getTicketManifest = session.createQuery(query);
        return (List<Tickets_People_Flights>) getTicketManifest.getResultList();
    }

    public static void checkinTicket(Tickets_People_Flights ticket){
        //Ticket is in ticket reference, just need to change field to "checked in"
        session.beginTransaction();
        TicketService.getTicketByID(ticket.getTicket_id());
        ticket.setChecked_in(true);
        session.update(ticket);
        session.getTransaction().commit();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void setSessionFactory(SessionFactory sessionFactory) {
        TicketService.sessionFactory = sessionFactory;
    }

    public static Session getSession() {
        return session;
    }

    public static void setSession(Session session) {
        TicketService.session = session;
    }
}
