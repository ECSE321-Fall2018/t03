package ca.mcgill.ecse321.ridesharing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.ridesharing.model.*;
import ca.mcgill.ecse321.ridesharing.repository.RideSharingRepository;

@RestController
public class RideSharingController {

	@Autowired
	RideSharingRepository repository;

	@RequestMapping("/")
	public String greeting() {
		return "Welcome to the ride sharing app!";
	}

	@PostMapping("/passenger/{username}/{password}")
	public String createParticipant(@PathVariable("username") String username, @PathVariable ("password") String password) {
		Passenger passenger = repository.createPassenger(username, password);
		return passenger.getUsername();
	}

	@GetMapping("/passenger/{username}")
	public String getPassenger(@PathVariable("username") String username) {
		Passenger passenger = repository.getPassenger(username);
		if(passenger == null) {
			return "NOT FOUND!";
		}
		return passenger.getUsername();
	}
	
	@PostMapping("/driver/{username}/{password}")
	public String createDriver(@PathVariable("username") String username, @PathVariable ("password") String password) {
		Driver driver = repository.createDriver(username,password); 
		return driver.getUsername();
	}
	
	@GetMapping("/driver/{username}")
	public String getDriver(@PathVariable("username") String username) {
		Driver driver = repository.getDriver(username);
		if(driver == null) {
			return "NOT FOUND!";
		}
		return driver.getUsername();
	}

}