package servlets;

import models.Tickets_People_Flights;
import services.FlightService;
import services.PeopleService;
import services.TicketService;
import utils.JSONSplitter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class zzServletModelTemplate extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        /**
         * Servlet template
         */
        System.out.println("DEBUG- SERVER REACHED");//take this out after debug finished

        //These lines read the request body and put it into a string called jsonText
        InputStream requestBody = req.getInputStream();
        Scanner sc = new Scanner(requestBody, StandardCharsets.UTF_8.name());
        String jsonText = sc.useDelimiter("\\A").next();
        //This will be a set of key-value pairs, like "numberOfTickets": 3, "userFlightID":4, "userCancelTickeID": 4

        System.out.println("DEBUG FlightID- JSON Text: " + jsonText);//take this out after debugging

        //get the action from the request header
        String action = req.getHeader("Servlet-action");
        System.out.println("DEBUG- action: "+action);

        switch(action){
            case "UserPurchaseTickets":
                //TODO: Write logic to add ticket(s) to DB here-
                // unique to action and the servlet, for example:
                // UserPurchaseTickets on tickets will need us to unmarshall a flight ID and a number of
                // tickets, and save that number of tickets in the DB using hibernate.
                // TODO: unmarshall
                // String[] namelikecase = JSONSplitter.jsonSplitter(jsonText);
                // TODO: setup objects, add data from unmarshalled JSON
                // TODO: instantiate new object of type ________
                // TODO: use setters to set field of ^
                // TODO: if (we need data from hibernate)... DO IT

//                Tickets_People_Flights addtpf = new Tickets_People_Flights(); // JSON{flight_id}, ticket_id,user_id
//                addtpf.setFlight();

                // TODO: call hibernate methods on objects
                // TODO: write response logic.. such as "Ticket's purchased: 5, for Chicago to LA"
                break;
            case "UserCancelTicket":
                //TODO: write logic to delete a ticket here
//        "userCancelTickeID": ucts.value
                break;
            case "UserCheckin":
                //TODO: write logic to checkIn here
//        "checkinTicketID": ctid.value,
        }

    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    }

}