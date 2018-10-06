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
	public Passenger createPassenger(String name) {
		Passenger passenger = new Passenger();
		passenger.setUsername(name);
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
	public Driver createDriver(String name) {
		Driver driver = new Driver();
		driver.setUsername(name);
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
	public Admin createAdmin(String name) {
		Admin admin = new Admin();
		admin.setUsername(name);
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
	public Route createRoute(Date aDate, Time aTime, List<Stop> stops, String vehicle, String startCity, int numberOfSeats) {
		Route route = new Route();
	    route.setAvailableSeats(numberOfSeats);
	    route.setDate(aDate);
	    route.setStartCity(startCity);
	    route.setStops(stops);
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
	
	@Transactional
	public List<Route> findRoutes(Date aDate, String startCity, String endCity){
		List<Route> routeList = entityManager.createQuery("SELECT r FROM Route r").getResultList();
		List<Route> matchingRoutes;
		matchingRoutes = new ArrayList<Route>();
		for(Route route: routeList) {
			Date date = route.getDate();
			String routeStartCity = route.getStartCity();
			List<Stop> stops = route.getStops();
			for(Stop stop: stops) {
				if(stop.equals(endCity) && date.equals(aDate) && routeStartCity.equals(startCity) && route.isIsAvailable()) {
					matchingRoutes.add(route);
				}
			}
		}
		return matchingRoutes;
	}
	
	@Transactional
	public void joinRoute(Route route, Passenger passenger) {
		int availableSeats = route.getAvailableSeats();
		List<Passenger> passengersOnRoute = route.getPassengers();
		if(passengersOnRoute.size()<availableSeats) {
			passengersOnRoute.add(passenger);
		}
		if(passengersOnRoute.size()==availableSeats) {
			route.setIsAvailable(false);
		}
	}
	
	

}
