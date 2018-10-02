package ca.mcgill.ecse321.ridesharing.repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.ridesharing.model.*;

@Repository
public class RideSharingRepository {

	@Autowired
	EntityManager entityManager;

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
	
	
	

}
