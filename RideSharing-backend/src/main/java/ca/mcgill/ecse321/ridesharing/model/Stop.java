package ca.mcgill.ecse321.ridesharing.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Stop{
private Route route;

@ManyToOne(optional=false)
public Route getRoute() {
   return this.route;
}

public void setRoute(Route route) {
   this.route = route;
}

private String city;

public void setCity(String value) {
this.city = value;
    }
public String getCity() {
return this.city;
    }
private String address;

public void setAddress(String value) {
this.address = value;
    }
public String getAddress() {
return this.address;
    }
private int costPerStop;

public void setCostPerStop(int value) {
this.costPerStop = value;
    }
public int getCostPerStop() {
return this.costPerStop;
       }
   }

