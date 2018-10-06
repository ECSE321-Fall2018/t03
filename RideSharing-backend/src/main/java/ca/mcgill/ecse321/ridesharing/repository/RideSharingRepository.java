package ca.mcgill.ecse321.ridesharing.repository;

import java.sql.Date;
import java.sql.Time;
import java.util.Set;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.ridesharing.model.*;

@Repository
public class RideSharingRepository {

	@Autowired
	private EntityManager entityManager;

	@Transactional
	public Passenger createPassenger(String name, String password) {
		Passenger passenger = new Passenger();
		passenger.setUsername(name);
		passenger.setPassword(password);
		passenger.setIsActive(true);
		entityManager.persist(passenger);
		return passenger;
	}

	@Transactional
	public Passenger getPassenger(String name) {
		Passenger passenger = entityManager.find(Passenger.class, name);
		return passenger;
	}
	
	
	@Transactional
	public Driver createDriver(String name, String password) {
		Driver driver = new Driver();
		driver.setUsername(name);
		driver.setPassword(password);
		driver.setIsActive(true);
		entityManager.persist(driver);
		return driver;
	}

	@Transactional
	public Driver getDriver(String name) {
		Driver driver  = entityManager.find(Driver.class, name);
		return driver;
	}
	
	@Transactional
	public Admin createAdmin(String name, String password) {
		Admin admin = new Admin();
		admin.setUsername(name);
		admin.setPassword(password);
		admin.setIsActive(true);
		entityManager.persist(admin);
		return admin;
	}

	@Transactional
	public Admin getAdmin(String name) {
		Admin admin  = entityManager.find(Admin.class, name);
		return admin;
	}
	
	@Transactional
	public Route createRoute(Date aDate, Time aTime, Set<Stop> stops, String vehicle, String startCity, int numberOfSeats) {
		Route route = new Route();
		String error;
		if(numberOfSeats == 0) {
			error = "Need to add seats";
		}
		if(aDate==null) {
			error = "Need date";
		}
		if(aTime==null) {
			error = "Need time";
		}
		if(vehicle==null) {
			error = "Need vehicle";
		}
		if(startCity==null) {
			error = "Need start city";
		}
		if(stops==null) {
			error = "Need stops";
		}
		
	    route.setAvailableSeats(numberOfSeats);
	    route.setDate(aDate);
	    route.setStartCity(startCity);
	    route.setStop(stops);
	    route.setVehicle(vehicle);
	    route.setIsAvailable(true);
	    route.setIsComplete(false);
	    entityManager.persist(route);
	    return route;
	}
	
	@Transactional
	public boolean routeFinished(Route route) {
		route.setIsComplete(true);
		return true;
		}
	
//	@Transactional
//	public Set<Route> findRoutes(Date aDate, String startCity, String endCity){
		
//	}
	
	@Transactional
	public void joinRoute(Route route) {
		int avaiableSeats = route.getAvailableSeats();
		if(avaiableSeats>0) {
			
		}
		
	}
	

}
