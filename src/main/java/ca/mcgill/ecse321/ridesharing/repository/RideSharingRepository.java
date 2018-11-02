package ca.mcgill.ecse321.ridesharing.repository;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.ridesharing.model.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import java.util.List;
import java.util.stream.Collectors;


import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class RideSharingRepository {

	@PersistenceContext
	EntityManager entityManager;
	
	//create user
	@Transactional
	public User createUser(String username, String password) {
		User user = new User();
		user.setRating(5);
		user.setUsername(username);
		user.setPassword(password);
		entityManager.persist(user);
		return user;	
	}
	
	//create route
	@Transactional
	public Route createRoute(int numberOfSeats, String startCity, String endCity, String aDate, String vehicle, String driver, String price) {
		
		Route route = new Route();
		route.setAvailableSeats(numberOfSeats);
		route.setStartCity(startCity);
		route.setEndCity(endCity);
		route.setDate(aDate);
		route.setIsAvailable(true);
	    route.setIsComplete(false);
	    route.setVehicle(vehicle);
	    route.setDriver(driver);
	    route.setPrice(price);
	    entityManager.persist(route);
	    return route;
	}
	
	//join route
	@Transactional
	public Route joinRoute(long id, String user) {
		
		TypedQuery<Route> query = entityManager.createQuery("SELECT c FROM Route c WHERE c.id = :id", Route.class);
		
		Route route = query.setParameter("id", id).getSingleResult();
		int seats = route.getAvailableSeats();
		
		if (seats == 6) {
			
			route.setPassenger1(user);
			
		} else if (seats == 5) {
			
			route.setPassenger2(user);
			
		} else if (seats == 4) {
			
			route.setPassenger3(user);
			
		} else if (seats == 3) {
			
			route.setPassenger4(user);
			
		} else if (seats == 2) {
			
			route.setPassenger5(user);
			
		} else  {
			
			route.setPassenger6(user);
			route.setIsAvailable(false);
			
		}
		
		route.setAvailableSeats(route.getAvailableSeats() - 1);
		entityManager.persist(route);
		
		return route;
	}
	
	//find route
	@Transactional
	public List<Route> findRoutes(String aDate, String startCity, String endCity){
	
		TypedQuery<Route> query = entityManager.createQuery("SELECT c FROM Route c WHERE c.date = :aDate"
				+ " AND c.startCity = :startCity AND c.endCity = :endCity AND c.isAvailable = TRUE AND c.isComplete = FALSE", Route.class);
		
		
		query.setParameter("startCity", startCity);
		query.setParameter("endCity", endCity);
		query.setParameter("aDate", aDate);
		
		return query.getResultList();

	}
	
	//end route
	@Transactional
	public Route endRoute(long id) {
			
		TypedQuery<Route> query = entityManager.createQuery("SELECT c FROM Route c WHERE c.id = :id", Route.class);
			
		Route route = query.setParameter("id", id).getSingleResult();
			
		route.setIsComplete(true);
			
		entityManager.persist(route);
			
		return route;
	}
		

	public List<Route> showDriversRoutes(String driver) {
			
		TypedQuery<Route> query = entityManager.createQuery("SELECT c FROM Route c WHERE c.driver = :driver AND c.isComplete = FALSE", Route.class);
			
		List<Route> routes = query.setParameter("driver", driver).getResultList();
			
		return routes;
	}

	public List<Route> showPassengerRoutes(String passenger) {
		
		TypedQuery<Route> query = entityManager.createQuery("SELECT c FROM Route c WHERE c.passenger1 = :passenger OR c.passenger2 = :passenger OR c.passenger3 = :passenger OR c.passenger4 = :passenger OR c.passenger5 = :passenger OR c.passenger6 = :passenger", Route.class);
		
		query.setParameter("passenger", passenger);
		
		List<Route> allRoutes = query.getResultList();
		List<Route> routes = new ArrayList<Route>();
		
		for (Route route : allRoutes) {
			
			if (!route.isIsComplete()) {
				
				routes.add(route);
				
			}
			
		}
			
		return routes;
		
	}
	
	
	
	

	
}