package models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 * This class is a model for resources in the flights table. It has 5 private
 * fields, a constructor, and Getters and Setters.
 */
@Entity
@Table(name="flights")
//Annotation helps hibernate set up the schema (format of the DB tables).
public class Flights {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer flight_id;

    @Column
    private String departure_city;

    @Column
    private String arrival_city;

//    @Column
//    private String date;
//
//    @Column
//    private String time;

    @Column
    private Boolean locked_For_Takeoff;

    @OneToMany(mappedBy = "flight",cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Tickets_People_Flights> ticketList;

    public Flights() {
    }

    public int getFlight_id() {
        return flight_id;
    }

    public void setFlight_id(int flight_id) {
        this.flight_id = flight_id;
    }

    public String getDeparture_city() {
        return departure_city;
    }

    public void setDeparture_city(String departure_city) {
        this.departure_city = departure_city;
    }

    public String getArrival_city() {
        return arrival_city;
    }

    public void setArrival_city(String arrival_city) {
        this.arrival_city = arrival_city;
    }

//    public String getDate() {
//        return date;
//    }

//    public void setDate(String date) {
//        this.date = date;
//    }
//
//    public String getTime() {
//        return time;
//    }
//
//    public void setTime(String time) {
//        this.time = time;
//    }

    public Boolean getLocked_For_Takeoff() {
        return locked_For_Takeoff;
    }

    public void setLocked_For_Takeoff(Boolean lockedForTakeoff) {
        this.locked_For_Takeoff = lockedForTakeoff;
    }
}
