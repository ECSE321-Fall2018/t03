package ca.mcgill.ecse321.ridesharing.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.OneToMany;

@Entity
public class Passenger extends User{
private Set<Request> request;

@OneToMany(mappedBy="passenger")
public Set<Request> getRequest() {
   return this.request;
}

public void setRequest(Set<Request> requests) {
   this.request = requests;
}

private Integer rating;

public void setRating(Integer value) {
this.rating = value;
    }
public Integer getRating() {
return this.rating;
       }
   }
