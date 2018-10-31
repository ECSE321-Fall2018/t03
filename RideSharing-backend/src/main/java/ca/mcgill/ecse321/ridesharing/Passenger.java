package ca.mcgill.ecse321.ridesharing;

import javax.persistence.Entity;
import java.util.List;
import javax.persistence.ManyToMany;

@Entity
public class Passenger extends User{
public Passenger(int i, String param1, String param2) {
		super(i, param1, param2);
		
	}

private Integer rating;

public void setRating(Integer value) {
   this.rating = value;
}

public int getRating() {
   return this.rating;
}

private List<Route> route;

@ManyToMany(mappedBy="passengers")
public List<Route> getRoute() {
   return this.route;
}

public void setRoute(List<Route> routes) {
   this.route = routes;
}

}
