package ca.mcgill.ecse321.ridesharing.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.ManyToMany;

@Entity
public class Passenger extends User{
private Integer rating;

public void setRating(Integer value) {
   this.rating = value;
}

public Integer getRating() {
   return this.rating;
}

private Set<Route> route;

@ManyToMany(mappedBy="passengers")
public Set<Route> getRoute() {
   return this.route;
}

public void setRoute(Set<Route> routes) {
   this.route = routes;
}

}
