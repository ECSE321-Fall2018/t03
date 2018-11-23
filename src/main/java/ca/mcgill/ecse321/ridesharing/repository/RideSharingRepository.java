package ca.mcgill.ecse321.ridesharing.repository;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	public User createUser(String username, String password, String type) {
		User user = new User();
		user.setRating(5);
		user.setUsername(username);
		user.setPassword(password);
		user.setRidesTravelled(0);
		user.setType(type);
		entityManager.persist(user);
		return user;	
	}
	
	//create route
	@Transactional
	public Route createRoute(int numberOfSeats, String startCity, String endCity, String aDate, String vehicle, String driver, String price) {
		
		TypedQuery<User> query = entityManager.createQuery("SELECT c FROM User c WHERE c.username = :username", User.class);
		
		User user = query.setParameter("username", driver).getSingleResult();
		
		user.setRidesTravelled(user.getRidesTravelled() + 1);
		
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
	    
	    //TypedQuery<Route> query = entityManager.createQuery("SELECT c FROM Route c WHERE c.availableSeats = :numberOfSeats AND c.date = :aDate ", Route.class);
	    
	    return route;
	}
	
	//join route
	@Transactional
	public Route joinRoute(long id, String user) {
		
		TypedQuery<User> userQuery = entityManager.createQuery("SELECT c FROM User c WHERE c.username = :username", User.class);
		
		User passenger = userQuery.setParameter("username", user).getSingleResult();
		
		passenger.setRidesTravelled(passenger.getRidesTravelled() + 1);
		
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
		entityManager.persist(passenger);
		
		return route;
	}
	
	//find route
	@Transactional
	public List<Route> findRoutes(String aDate, String startCity, String endCity){
	
		TypedQuery<Route> query = entityManager.createQuery("SELECT c FROM Route c WHERE c.date = :aDate"
				+ " AND c.startCity LIKE :startCity AND c.endCity LIKE :endCity AND c.isAvailable = TRUE AND c.isComplete = FALSE", Route.class);
		
		
		query.setParameter("startCity", "%"+startCity+"%");
		query.setParameter("endCity", "%"+endCity+"%");
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
		
	//show drivers routes
	@Transactional
	public List<Route> showDriversRoutes(String driver) {
			
		TypedQuery<Route> query = entityManager.createQuery("SELECT c FROM Route c WHERE c.driver = :driver AND c.isComplete = FALSE", Route.class);
			
		List<Route> routes = query.setParameter("driver", driver).getResultList();
			
		return routes;
	}
	
	//show passengers routes
	@Transactional
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

	//leave route
	@Transactional
	public Route leaveRoute(long id, String passenger) {
		
		boolean found = false;
		
		TypedQuery<Route> query = entityManager.createQuery("SELECT c FROM Route c WHERE c.id = :id", Route.class);
		
		Route route = query.setParameter("id", id).getSingleResult();
		
		if (route.getPassenger1() != null) {
		
			if (route.getPassenger1().equals(passenger)) {
			
				route.setPassenger1(null);
				found = true;
			
			}
			
		} else if (route.getPassenger2() != null) {
			
			if (route.getPassenger2().equals(passenger)) {
				
				route.setPassenger2(null);
				found = true;
			
			}
			
		} else if (route.getPassenger3() != null) {
			
			if (route.getPassenger3().equals(passenger)) {
				
				route.setPassenger3(null);
				found = true;
			
			}
			
		} else if (route.getPassenger4() != null) {
			
			if (route.getPassenger4().equals(passenger)) {
				
				route.setPassenger4(null);
				found = true;
			
			}
			
		} else if (route.getPassenger5() != null) {
			
			if (route.getPassenger5().equals(passenger)) {
				
				route.setPassenger5(null);
				found = true;
			
			}
			
		} else if (route.getPassenger6() != null) {
			
			if (route.getPassenger6().equals(passenger)) {
				
				route.setPassenger6(null);
				found = true;
			
			}
			
		}
		
		if (found) {
			
			route.setAvailableSeats(route.getAvailableSeats() + 1);
			entityManager.persist(route);
			
		}
		
		return route;
		
	}

	//rankUser
	@Transactional
	public User rateUser(String username, int rating) {
		
		TypedQuery<User> query = entityManager.createQuery("SELECT c FROM User c WHERE c.username = :username", User.class);
		
		User user = query.setParameter("username", username).getSingleResult();
			
		int avgRating = user.getRating();
		int ridesTravelled = user.getRidesTravelled();
		int total = avgRating*(ridesTravelled);
		avgRating = (total + rating)/(ridesTravelled + 1);
		user.setRating(avgRating);
		user.setRidesTravelled(ridesTravelled);
			
		entityManager.persist(user);
		
		return user;
	}
	
	//create route
	@Transactional
	public Route modifyRoute(Integer numberOfSeats, String startCity, String endCity, String aDate, String vehicle, String price, long id) {
			
		TypedQuery<Route> query = entityManager.createQuery("SELECT c FROM Route c WHERE c.id = :id", Route.class);
		
		Route route = query.setParameter("id", id).getSingleResult();
		
		route.setStartCity(startCity);
		route.setEndCity(endCity);
		route.setDate(aDate);
		route.setVehicle(vehicle);
		route.setPrice(price);
		int people = 0;
		
		if (route.getPassenger1() != null) {
			people++;
		} if (route.getPassenger2() != null) {
			people++;
		} if (route.getPassenger3() != null) {
			people++;
		} if (route.getPassenger4() != null) {
			people++;
		} if (route.getPassenger5() != null) {
			people++;
		} if (route.getPassenger6() != null) {
			people++;
		}
		
		if (numberOfSeats > people) {
			
			route.setIsAvailable(true);
			route.setAvailableSeats(numberOfSeats - people); 
			
		} 
		
		entityManager.persist(route);
		return route;
	}
	
	public List<User> findUsers(String username, String type) {
		
		List<User> users;
		
		if (username.equals("!ALL!")) {
			TypedQuery<User> query = entityManager.createQuery("SELECT c FROM User c WHERE c.type LIKE :type", User.class);
			query.setParameter("type", "%"+type+"%");
			users = query.getResultList();
		} else {
			TypedQuery<User> query = entityManager.createQuery("SELECT c FROM User c WHERE c.username LIKE :username AND c.type LIKE :type", User.class);
			query.setParameter("username", "%"+username+"%");
			query.setParameter("type", "%"+type+"%");
			users = query.getResultList();
		}	
		
		Collections.sort(users, new Comparator<User>(){
		     public int compare(User o1, User o2){
		         if(o1.getRating() == o2.getRating())
		             return 0;
		         return o1.getRating() < o2.getRating() ? 1 : -1;
		     }
		});
		
		return users;
	}

	
	public List<User> findUsersInRange(String startDate, String endDate, String type) {
		
		TypedQuery<Route> query = entityManager.createQuery("SELECT c FROM Route c", Route.class);
		List<Route> routes = query.getResultList();
		
		List<Route> rangeRoutes = new ArrayList<Route>();
		
		String [] start = startDate.split("-");
		String [] end = endDate.split("-");
		
		int startD = Integer.parseInt(start[2])*365 + Integer.parseInt(start[1]) * 30 + Integer.parseInt(start[0]);
		int endD = Integer.parseInt(end[2])*365 + Integer.parseInt(end[1]) * 30 + Integer.parseInt(end[0]);
		
		for (Route route : routes) {
			
			String date = route.getDate();
			
			String [] current = date.split("-");
			
			int currentD = Integer.parseInt(current[2])*365 + Integer.parseInt(current[1]) * 30 + Integer.parseInt(current[0]);
			
			if (currentD >= startD && currentD <= endD) {
				
				System.out.println(route.getDriver());
				rangeRoutes.add(route);
				
			}
			
		}
		
		List<User> users = new ArrayList<User>();
		
		if (type.equals("Driver")) {
			
			System.out.println("hello Driver");
			
			for (Route route : rangeRoutes) {
				
				System.out.println(route);
				
				String driver = route.getDriver();
				TypedQuery<User> driverQuery = entityManager.createQuery("SELECT c FROM User c WHERE c.username = :username", User.class);
				User user = driverQuery.setParameter("username", driver).getSingleResult();
				
				System.out.println(user.getUsername());
				
				users.add(user);
				
			}
			
		} else if (type.equals("Passenger")){
			
			for (Route route : rangeRoutes) {
				
				String passenger1 = route.getPassenger1();
				String passenger2 = route.getPassenger2();
				String passenger3 = route.getPassenger3();
				String passenger4 = route.getPassenger4();
				String passenger5 = route.getPassenger5();
				String passenger6 = route.getPassenger6();
				
				if (passenger1 != null) {
			
					TypedQuery<User> driverQuery = entityManager.createQuery("SELECT c FROM User c WHERE c.username = :username", User.class);
					User user = driverQuery.setParameter("username", passenger1).getSingleResult();
					users.add(user);
				}
				if (passenger2 != null) {
					
					TypedQuery<User> driverQuery = entityManager.createQuery("SELECT c FROM User c WHERE c.username = :username", User.class);
					User user = driverQuery.setParameter("username", passenger2).getSingleResult();
					users.add(user);
				}
				if (passenger3 != null) {
					
					TypedQuery<User> driverQuery = entityManager.createQuery("SELECT c FROM User c WHERE c.username = :username", User.class);
					User user = driverQuery.setParameter("username", passenger3).getSingleResult();
					users.add(user);
				}
				if (passenger4 != null) {
					
					TypedQuery<User> driverQuery = entityManager.createQuery("SELECT c FROM User c WHERE c.username = :username", User.class);
					User user = driverQuery.setParameter("username", passenger4).getSingleResult();
					users.add(user);
				}
				if (passenger5 != null) {
					
					TypedQuery<User> driverQuery = entityManager.createQuery("SELECT c FROM User c WHERE c.username = :username", User.class);
					User user = driverQuery.setParameter("username", passenger5).getSingleResult();
					users.add(user);
				}
				if (passenger6 != null) {
					
					TypedQuery<User> driverQuery = entityManager.createQuery("SELECT c FROM User c WHERE c.username = :username", User.class);
					User user = driverQuery.setParameter("username", passenger6).getSingleResult();
					users.add(user);
				}
						
			}

			
		}
		
		Set<User> hs = new HashSet<>();
		hs.addAll(users);
		users.clear();
		users.addAll(hs);
		
		Collections.sort(users, new Comparator<User>(){
		     public int compare(User o1, User o2){
		         if(o1.getRating() == o2.getRating())
		             return 0;
		         return o1.getRating() < o2.getRating() ? 1 : -1;
		     }
		});
		
		return users;
	}

	public List<String> findActiveUsers(String type) {
		
		return null;
	}

	public List<Route> findActiveRoutes() {
		
		return null;
	}

	public List<Route> mostPopularRoutes(String startDate, String endDate) {
		
		TypedQuery<Route> query = entityManager.createQuery("SELECT c FROM Route c", Route.class);
		List<Route> routes = query.getResultList();
		
		List<Route> rangeRoutes = new ArrayList<Route>();
		
		String [] start = startDate.split("-");
		String [] end = endDate.split("-");
		
		int startD = Integer.parseInt(start[2])*365 + Integer.parseInt(start[1]) * 30 + Integer.parseInt(start[0]);
		int endD = Integer.parseInt(end[2])*365 + Integer.parseInt(end[1]) * 30 + Integer.parseInt(end[0]);
		
		for (Route route : routes) {
			
			String date = route.getDate();
			
			String [] current = date.split("-");
			
			int currentD = Integer.parseInt(current[2])*365 + Integer.parseInt(current[1]) * 30 + Integer.parseInt(current[0]);
			
			if (currentD >= startD && currentD <= endD) {
				
				System.out.println(route.getDriver());
				rangeRoutes.add(route);
				
			}
			
		}
		
		return rangeRoutes;
	}

	public List<User> mostLoyal(String startDate, String endDate, String type) {
		
		TypedQuery<Route> query = entityManager.createQuery("SELECT c FROM Route c", Route.class);
		List<Route> routes = query.getResultList();
		
		List<Route> rangeRoutes = new ArrayList<Route>();
		
		String [] start = startDate.split("-");
		String [] end = endDate.split("-");
		
		int startD = Integer.parseInt(start[2])*365 + Integer.parseInt(start[1]) * 30 + Integer.parseInt(start[0]);
		int endD = Integer.parseInt(end[2])*365 + Integer.parseInt(end[1]) * 30 + Integer.parseInt(end[0]);
		
		for (Route route : routes) {
			
			String date = route.getDate();
			
			String [] current = date.split("-");
			
			int currentD = Integer.parseInt(current[2])*365 + Integer.parseInt(current[1]) * 30 + Integer.parseInt(current[0]);
			
			if (currentD >= startD && currentD <= endD) {
				
				System.out.println(route.getDriver());
				rangeRoutes.add(route);
				
			}
			
		}
		
		List<User> users = new ArrayList<User>();
		
		if (type.equals("Driver")) {
			
			System.out.println("hello Driver");
			
			for (Route route : rangeRoutes) {
				
				System.out.println(route);
				
				String driver = route.getDriver();
				TypedQuery<User> driverQuery = entityManager.createQuery("SELECT c FROM User c WHERE c.username = :username", User.class);
				User user = driverQuery.setParameter("username", driver).getSingleResult();
				
				System.out.println(user.getUsername());
				
				users.add(user);
				
			}
			
		} else if (type.equals("Passenger")){
			
			for (Route route : rangeRoutes) {
				
				String passenger1 = route.getPassenger1();
				String passenger2 = route.getPassenger2();
				String passenger3 = route.getPassenger3();
				String passenger4 = route.getPassenger4();
				String passenger5 = route.getPassenger5();
				String passenger6 = route.getPassenger6();
				
				if (passenger1 != null) {
			
					TypedQuery<User> driverQuery = entityManager.createQuery("SELECT c FROM User c WHERE c.username = :username", User.class);
					User user = driverQuery.setParameter("username", passenger1).getSingleResult();
					users.add(user);
				}
				if (passenger2 != null) {
					
					TypedQuery<User> driverQuery = entityManager.createQuery("SELECT c FROM User c WHERE c.username = :username", User.class);
					User user = driverQuery.setParameter("username", passenger2).getSingleResult();
					users.add(user);
				}
				if (passenger3 != null) {
					
					TypedQuery<User> driverQuery = entityManager.createQuery("SELECT c FROM User c WHERE c.username = :username", User.class);
					User user = driverQuery.setParameter("username", passenger3).getSingleResult();
					users.add(user);
				}
				if (passenger4 != null) {
					
					TypedQuery<User> driverQuery = entityManager.createQuery("SELECT c FROM User c WHERE c.username = :username", User.class);
					User user = driverQuery.setParameter("username", passenger4).getSingleResult();
					users.add(user);
				}
				if (passenger5 != null) {
					
					TypedQuery<User> driverQuery = entityManager.createQuery("SELECT c FROM User c WHERE c.username = :username", User.class);
					User user = driverQuery.setParameter("username", passenger5).getSingleResult();
					users.add(user);
				}
				if (passenger6 != null) {
					
					TypedQuery<User> driverQuery = entityManager.createQuery("SELECT c FROM User c WHERE c.username = :username", User.class);
					User user = driverQuery.setParameter("username", passenger6).getSingleResult();
					users.add(user);
				}
						
			}

			
		}
		
		Set<User> hs = new HashSet<>();
		hs.addAll(users);
		users.clear();
		users.addAll(hs);
		
		Collections.sort(users, new Comparator<User>(){
		     public int compare(User o1, User o2){
		         if(o1.getRidesTravelled() == o2.getRidesTravelled())
		             return 0;
		         return o1.getRidesTravelled() < o2.getRidesTravelled() ? 1 : -1;
		     }
		});
		
		return users;
	}
	
}