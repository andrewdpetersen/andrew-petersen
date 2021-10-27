package services;

import models.Flights;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.LinkedList;
import java.util.List;

public class FlightService {
    private static Session session = HibernateManagement.getSession();
    private static SessionFactory sessionFactory = HibernateManagement.getSessionFactory();


    /**
     * This method save a Flights instance to our database.
     * @param flight - the Flights instance we want to save in our database
     */
    //This doesn't need a unit test
    public static void saveNewFlight(Flights flight){
        System.out.println("DEBUG: saveNewFlight method called");
        session.save(flight);
        System.out.println("DEBUG: Flight saved in DB");
    }

    /**
     * This method fetches a flight from our database with the given flight id.
     * @param flight_id - the id of the flight that we want to find
     * @return Flights
     */
    //This doesn't need a unit test
    public static Flights getFlightById(int flight_id){
        System.out.println("DEBUG: getFlightById method called");
        return session.get(Flights.class, flight_id);
    }


    /**
     * This method is incomplete... we need to adjust it to limit our search by cities.
     * @param departureCity - String, the city all flights in the list are departing from
     * @param arrivalCity - String, the city all flights in the list are arriving at
     * @return
     */
    public static List<Flights> getFlightsByArrivalDestination(String departureCity, String arrivalCity){
        List<Flights> flightsList = new LinkedList<>();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Flights> query = builder.createQuery(Flights.class);
        Root<Flights> root = query.from(Flights.class);
        query.select(root).where(builder.and(builder.equal(root.get("departure_city"),departureCity),
                builder.equal(root.get("arrival_city"),arrivalCity)));
        Query userFlightsByDepAndArr = session.createQuery(query);
        return userFlightsByDepAndArr.getResultList();
    }

    /**
     * This method deletes a Flights instance.
     * @param flight - the Flights instance that is being deleted
     */
    //This doesn't need a unit test.
    public static void deleteFlight(Flights flight){
        session.beginTransaction();
        session.delete(flight);
        session.getTransaction().commit();
    }


    //Maybe more get methods based on needs...
    public static void PilotTakeoffLock(Flights flight){
        session.beginTransaction();
        System.out.println("DEBUG: PilotTakeoffLock method called");
        Flights takeoffFlight = session.load(Flights.class, flight.getFlight_id());
        System.out.println("DEBUG: session.load flight successful");
        takeoffFlight.setLocked_For_Takeoff(true);
        session.save(takeoffFlight);
        session.getTransaction().commit();
    }

    public static Session getSession(){
        return session;
    }
    public static void setSession(Session session) {
        FlightService.session = session;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void setSessionFactory(SessionFactory sessionFactory) {
        FlightService.sessionFactory = sessionFactory;
    }







}
