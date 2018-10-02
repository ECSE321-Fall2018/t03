package ca.mcgill.ecse321.ridesharing.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.ManyToMany;

@Entity
public class Request extends Role{
private boolean isAdmin;
   
   public void setIsAdmin(boolean value) {
this.isAdmin = value;
    }
public boolean isIsAdmin() {
return this.isAdmin;
    }
private Set<Request> request;

@ManyToMany(mappedBy="participant1")
public Set<Request> getRequest() {
   return this.request;
}

public void setRequest(Set<Request> requests) {
   this.request = requests;
}

private Set<Route> route;

@ManyToMany(mappedBy="participant1")
public Set<Route> getRoute() {
   return this.route;
}

public void setRoute(Set<Route> routes) {
   this.route = routes;
}

}
