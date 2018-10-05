package ca.mcgill.ecse321.ridesharing.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;

@Entity
public class Route{
private String vehicle;
   
   public void setVehicle(String value) {
this.vehicle = value;
    }
@Id
public String getVehicle() {
return this.vehicle;
    }
private Integer availableSeats;

public void setAvailableSeats(Integer value) {
   this.availableSeats = value;
}

public Integer getAvailableSeats() {
   return this.availableSeats;
}

private String startCity;

public void setStartCity(String value) {
   this.startCity = value;
}

public String getStartCity() {
   return this.startCity;
}

/**
 * <pre>
 *           1..1     1..1
 * Route ------------------------> Date
 *           &lt;       date
 * </pre>
 */
private Date date;

public void setDate(Date value) {
   this.date = value;
}

public Date getDate() {
   return this.date;
}

private Driver driver;

public void setDriver(Driver value) {
this.driver = value;
    }
public Driver getDriver() {
return this.driver;
    }
private Set<Stop> stop;

@OneToMany(mappedBy="route", cascade={CascadeType.ALL})
public Set<Stop> getStop() {
   return this.stop;
}

public void setStop(Set<Stop> stops) {
   this.stop = stops;
}

private Set<Passenger> passenger;

@ManyToMany
public Set<Passenger> getPassenger() {
   return this.passenger;
}

public void setPassenger(Set<Passenger> passengers) {
   this.passenger = passengers;
}

}
