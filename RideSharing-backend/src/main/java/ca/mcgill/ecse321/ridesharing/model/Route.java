package ca.mcgill.ecse321.ridesharing.model;

import javax.persistence.Entity;
import java.sql.Date;
import java.util.List;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.sql.Time;

@Entity
public class Route{
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

/**
 * <pre>
 *           1..1     1..1
 * Route ------------------------> Driver
 *           &lt;       driver
 * </pre>
 */
private Driver driver;

public void setDriver(Driver value) {
   this.driver = value;
}

public Driver getDriver() {
   return this.driver;
}

private List<Passenger> passengers;

@ManyToMany
public List<Passenger> getPassengers() {
   return this.passengers;
}

public void setPassengers(List<Passenger> passengerss) {
   this.passengers = passengerss;
}

private Driver driver1;

@ManyToOne(optional=false)
public Driver getDriver1() {
   return this.driver1;
}

public void setDriver1(Driver driver1) {
   this.driver1 = driver1;
}

private boolean isAvailable;

public void setIsAvailable(boolean value) {
   this.isAvailable = value;
}

public boolean isIsAvailable() {
   return this.isAvailable;
}

private boolean isComplete;

public void setIsComplete(boolean value) {
   this.isComplete = value;
}

public boolean isIsComplete() {
   return this.isComplete;
}

/**
 * <pre>
 *           1..1     1..1
 * Route ------------------------> Time
 *           &lt;       time
 * </pre>
 */
private Time time;

public void setTime(Time value) {
   this.time = value;
}

public Time getTime() {
   return this.time;
}

private String vehicle;

public void setVehicle(String value) {
this.vehicle = value;
    }
public String getVehicle() {
return this.vehicle;
       }
   }
