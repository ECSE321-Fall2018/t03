package ca.mcgill.ecse321.ridesharing;

import javax.persistence.Entity;
import java.util.List;
import javax.persistence.ManyToMany;

@Entity
public class Admin extends User{
public Admin(int i, String param1, String param2) {
		super(i, param1, param2);
		
	
	}

private List<Passenger> passenger;

@ManyToMany
public List<Passenger> getPassenger() {
   return this.passenger;
}

public void setPassenger(List<Passenger> passengers) {
   this.passenger = passengers;
}

private List<Driver> driver1;

@ManyToMany
public List<Driver> getDriver1() {
   return this.driver1;
}

public void setDriver1(List<Driver> driver1s) {
   this.driver1 = driver1s;
}

}
