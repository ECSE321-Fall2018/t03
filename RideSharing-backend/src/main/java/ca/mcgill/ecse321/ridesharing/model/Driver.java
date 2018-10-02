package ca.mcgill.ecse321.ridesharing.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.OneToMany;

@Entity
public class Driver extends User{
private Set<Route> route;

@OneToMany(mappedBy="driver")
public Set<Route> getRoute() {
   return this.route;
}

public void setRoute(Set<Route> routes) {
   this.route = routes;
}

private Integer rating;

public void setRating(Integer value) {
this.rating = value;
    }
public Integer getRating() {
return this.rating;
       }
   }
