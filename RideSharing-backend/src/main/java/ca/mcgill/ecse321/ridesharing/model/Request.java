package ca.mcgill.ecse321.ridesharing.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Set;
import javax.persistence.ManyToMany;

@Entity
public class Request{
private String startCIty;
   
   public void setStartCIty(String value) {
this.startCIty = value;
    }
  @Id
public String getStartCIty() {
return this.startCIty;
    }
private String endCity;

public void setEndCity(String value) {
this.endCity = value;
    }

public String getEndCity() {
return this.endCity;
    }
private Passenger passenger;


@ManyToOne(optional=false)
public Passenger getPassenger() {
   return this.passenger;
}

public void setPassenger(Passenger passenger) {
   this.passenger = passenger;
}

private Set<Route> route;


@ManyToMany
public Set<Route> getRoute() {
   return this.route;
}

public void setRoute(Set<Route> routes) {
   this.route = routes;
}

}
