package models;

import javax.persistence.*;
import java.util.List;

/**
 * This class is our model for resources in the people table. It has 4 private fields,
 * a constructor, and public Getters and Setters.
 */
@Entity
@Table(name="people")
public class People {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer people_id;

    @Column
    private String username;

    @Column
    private Integer access_level;  // CAN A PILOT BE A PASSENGER??? user=0,pilot=1,admin=2

    @Column
    private String password;

    @OneToMany(mappedBy = "person",cascade = CascadeType.REMOVE)
    private List<Tickets_People_Flights> ticketList;

    //constructor
    public People() {
    }

    public Integer getPeople_id() {
        return people_id;
    }

    public void setPeople_id(int people_id) {
        this.people_id = people_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAccess_level() {
        return access_level;
    }

    public void setAccess_level(int access_level) {
        this.access_level = access_level;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
