package ca.mcgill.ecse321.ridesharing.controller;

//import java.sql.Date;
//import java.sql.Time;
//
import ca.mcgill.ecse321.ridesharing.model.*;
//import ca.mcgill.ecse321.ridesharing.repository.*;


import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import ca.mcgill.ecse321.ridesharing.model.User;
import ca.mcgill.ecse321.ridesharing.repository.RideSharingRepository;


@RestController
//@RequestMapping("api/user")
public class RideSharingController {

	@Autowired
	RideSharingRepository repository;
	
	@RequestMapping("/")
	public String greeting () 
	{
		return "Hello world!";
	}
	
	@PostMapping("/create/{username}")
	//@ResponseBody
	public String createUser(@PathVariable String username) {
		String password = "123";

		User user = repository.createUser(username, password);
		if (user != null) {
			return " user was created!";
		} else {
			return "user could not be created.";
		}
		
	}
	/*
	@RequestMapping(value = "/find/{username}", method = RequestMethod.GET)
	public User getVehicle(@PathVariable("username") String username) {
		return repository.getUser(username);
	}
	
	@RequestMapping("/")
	public String greeting() {
		return "<h1>Welcome to the ride sharing app!</h1>";
	}


	@PostMapping("/passenger/{username}")
	public String createParticipant(@PathVariable String username) {
		Passenger passenger = repository.createPassenger(username);
		
		// create connection with jdbc
		// insert into query statement 
		
		return passenger.getUsername();
	}

	@GetMapping("/passenger/{username}")
	public String getPassenger(@PathVariable String username) {
		Passenger passenger = repository.getPassenger(username);
		if(passenger == null) {
			return "NOT FOUND!";
		}
		return passenger.getUsername();
	}
	
	@PostMapping("/driver/{username}")
	public String createDriver(@PathVariable String username) {
		Driver driver = repository.createDriver(username); 
		return driver.getUsername();
	}
	
	@GetMapping("/driver/{username}")
	public String getDriver(@PathVariable String username) {
		Driver driver = repository.getDriver(username);
		if(driver == null) {
			return "NOT FOUND!";
		}
		return driver.getUsername();
	}
	@PostMapping("/{date}/{time}/{stop}/{car}/{startCity}/{numberOfSeats}")
	
		public void createRoute(@PathVariable Date date, @PathVariable Time time, String car, String startCity, int numberOfSeats) {
			repository.createRoute(date, time, car, startCity, numberOfSeats);
			
		}
		
		*/
	}

