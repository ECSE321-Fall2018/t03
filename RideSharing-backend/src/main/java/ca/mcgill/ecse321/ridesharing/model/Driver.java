package ca.mcgill.ecse321.ridesharing.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import java.util.Set;

@Entity
public class Driver extends User{
private Set<Route> route;
public void setRoute (Set <Route> routes)
{
this.route = routes;
   }
@Id
@OneToMany(mappedBy = "driver")
public Set<Route> getRoute ()
{
return this.route;
   }
private Integer rating;

public void setRating(Integer value) {
   this.rating = value;
}

public Integer getRating() {
   return this.rating;
}

}
