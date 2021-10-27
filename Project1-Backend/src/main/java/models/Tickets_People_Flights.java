package models;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

/**
 * This class is a model for resources in our tickets_people_flights table.
 * It has 3 private fields, a constructor, and Getters and Setters. It is an example of
 * encapsulation, and it helps us abstract by obscuring the functionality inside the class.
 */
@Entity // mapped CLASS
@Table(name = "tickets")  //sets the table
public class Tickets_People_Flights {
    @Id  // mark as a primary key
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ticket_id;

    @Column
    private Boolean checked_in;

    @ManyToOne
    @JoinColumn(nullable=false)
    private People person;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Flights flight;

    public Tickets_People_Flights() {
    }

    public Integer getTicket_id() {
        return ticket_id;
    }
    public void setTicket_id(Integer ticket_id) {
        this.ticket_id = ticket_id;
    }
    public People getPerson() {return person;}
    public void setPerson(People person) {this.person = person;}
    public Flights getFlight() {return flight;}
    public void setFlight(Flights flight) {this.flight = flight;}
    public Boolean getChecked_in() {
        return checked_in;
    }
    public void setChecked_in(Boolean checked_in) {
        this.checked_in = checked_in;
    }
}
