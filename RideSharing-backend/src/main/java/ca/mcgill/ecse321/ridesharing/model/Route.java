package ca.mcgill.ecse321.ridesharing.model;

import javax.persistence.Entity;
import java.sql.Date;
import javax.persistence.ManyToOne;
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
private Date date;

public void setDate(Date value) {
this.date = value;
    }
public Date getDate() {
return this.date;
    }
private Driver driver;

@ManyToOne(optional=false)
public Driver getDriver() {
   return this.driver;
}

public void setDriver(Driver driver) {
   this.driver = driver;
}

private Set<Stop> stop;

@OneToMany(mappedBy="route", cascade={CascadeType.ALL})
public Set<Stop> getStop() {
   return this.stop;
}

public void setStop(Set<Stop> stops) {
   this.stop = stops;
}

private Set<Request> request;

@ManyToMany(mappedBy="route")
public Set<Request> getRequest() {
   return this.request;
}

public void setRequest(Set<Request> requests) {
   this.request = requests;
}

}
