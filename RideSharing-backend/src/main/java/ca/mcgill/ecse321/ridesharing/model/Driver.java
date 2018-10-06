package ca.mcgill.ecse321.ridesharing.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

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

private Set<Route> routes;

@OneToMany(mappedBy="driver1", cascade={CascadeType.ALL})
public Set<Route> getRoutes() {
   return this.routes;
}

public void setRoutes(Set<Route> routess) {
   this.routes = routess;
}

private String email;

public void setEmail(String value) {
this.email = value;
    }
public String getEmail() {
return this.email;
       }
   }
