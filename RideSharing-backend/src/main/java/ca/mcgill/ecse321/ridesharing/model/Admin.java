package ca.mcgill.ecse321.ridesharing.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.ManyToMany;

@Entity
public class Admin extends User{
private Set<Passenger> passenger;

@ManyToMany
public Set<Passenger> getPassenger() {
   return this.passenger;
}

public void setPassenger(Set<Passenger> passengers) {
   this.passenger = passengers;
}

private Set<Driver> driver1;

@ManyToMany
public Set<Driver> getDriver1() {
   return this.driver1;
}

public void setDriver1(Set<Driver> driver1s) {
   this.driver1 = driver1s;
}

}
