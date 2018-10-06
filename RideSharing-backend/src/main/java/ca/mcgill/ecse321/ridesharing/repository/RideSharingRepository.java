package ca.mcgill.ecse321.ridesharing.repository;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;


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
		Passenger existingPassenger = entityManager.find(Passenger.class, name);
		if(existingPassenger!=null) {
			return null;
		}
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
	public Route createRoute(Date aDate, Time aTime, String vehicle, String startCity, String endCity, int numberOfSeats) {
		Route route = new Route();
	    route.setAvailableSeats(numberOfSeats);
	    route.setDate(aDate);
	    route.setStartCity(startCity);
	    route.setEndCity(endCity);
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
	/*
	 * Figure out query stuff
	 */
	public List<Route> findRoutes(Date aDate, String startCity, String endCity){
		List<Route> routeList = entityManager.createQuery("SELECT r FROM Route r").getResultList();
		List<Route> matchingRoutes;
		matchingRoutes = new ArrayList<Route>();
		for(Route route: routeList) {
			Date date = route.getDate();
			String routeEndCity = route.getEndCity();
			String routeStartCity = route.getStartCity();
			if(routeStartCity.equals(startCity)&&routeEndCity.equals(endCity)&&aDate.equals(date)){
				matchingRoutes.add(route);
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
	
	@Transactional
	public void rateDriver(Driver driver, int rating) {
		int ratingSum =0;
		int newRating = 0;
		List<Route> pastRoutes = driver.getRoutes();
		int totalPastPassengers  = 0;
		int passengersPerRoute = 0;
		for(Route route : pastRoutes) {
			passengersPerRoute = route.getPassengers().size();
			totalPastPassengers = totalPastPassengers + passengersPerRoute;
		}
		int currentRating = driver.getRating();
		ratingSum = currentRating*(totalPastPassengers-1);
		newRating = (ratingSum+rating)/totalPastPassengers;
		driver.setRating(newRating);
		entityManager.persist(driver);
	}
	
	@Transactional
	public void ratePassenger(Passenger passenger, int rating) {
		int ratingSum =0;
		int newRating = 0;
		List<Route> pastRoutes = passenger.getRoute();
		int currentRating = passenger.getRating();
		int numberOfTotal = pastRoutes.size();
		ratingSum = currentRating*(numberOfTotal-1);
		newRating = (ratingSum+rating)/numberOfTotal;
		passenger.setRating(newRating);
		entityManager.persist(passenger);
	}
	
	public List<Driver> getDriverRatings(){
		List<Driver> drivers = entityManager.createQuery("SELECT d FROM Driver d").getResultList();
		List<Driver> bestDrivers = new ArrayList<Driver>();
		for(Driver driver: drivers)
		{
			if(driver.getRating()<5) {
				bestDrivers.add(driver);
			}
		}
		return bestDrivers;
	}

}
