package ca.mcgill.ecse321.ridesharing.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;
import java.sql.Date;
import java.sql.Time;


@Entity
public class Route{
private String make;
   
   public void setMake(String value) {
this.make = value;
    }
public String getMake() {
return this.make;
    }
private String model;

public void setModel(String value) {
this.model = value;
    }
public String getModel() {
return this.model;
    }
private int seats;

public void setSeats(int value) {
this.seats = value;
    }
public int getSeats() {
return this.seats;
    }
private int costPerStop;

public void setCostPerStop(int value) {
this.costPerStop = value;
    }
public int getCostPerStop() {
return this.costPerStop;
    }
private Set<Stop> stops;

@OneToMany(mappedBy="route", cascade={CascadeType.ALL})
public Set<Stop> getStops() {
   return this.stops;
}

public void setStops(Set<Stop> stopss) {
   this.stops = stopss;
}

private Set<Request> request;

@ManyToMany
public Set<Request> getRequest() {
   return this.request;
}

public void setRequest(Set<Request> requests) {
   this.request = requests;
}

private Set<Participant> participant1;

@ManyToMany
public Set<Participant> getParticipant1() {
   return this.participant1;
}

public void setParticipant1(Set<Participant> participant1s) {
   this.participant1 = participant1s;
}

private Date startDate;

public void setStartDate(Date value) {
this.startDate = value;
    }
public Date getStartDate() {
return this.startDate;
    }
private Time startTime;

public void setStartTime(Time value) {
this.startTime = value;
    }
public Time getStartTime() {
return this.startTime;
       }
   }