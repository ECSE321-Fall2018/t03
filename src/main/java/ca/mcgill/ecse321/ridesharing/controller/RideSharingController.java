package ca.mcgill.ecse321.ridesharing.controller;

//import java.sql.Date;
//import java.sql.Time;
//
import ca.mcgill.ecse321.ridesharing.model.*;
//import ca.mcgill.ecse321.ridesharing.repository.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//import ca.mcgill.ecse321.ridesharing.model.User;
import ca.mcgill.ecse321.ridesharing.repository.RideSharingRepository;


@RestController
public class RideSharingController {

	@Autowired
	RideSharingRepository repository;
	
	@RequestMapping("/")
	public String greeting () 
	{
		return "Hello world!";
	}
	
	//sign in
	@RequestMapping(value = "/signIn/{username}/{password}", method = RequestMethod.POST)
	@ResponseBody
	public String createUser(@PathVariable String username, @PathVariable String password) {
		
		try {
			
			User user = repository.createUser(username, password);
			
			if (user != null) {
				return " user was created!";
			} else {
				return "user could not be created.";
			}
			
		} catch (Exception e) {
			
			return "sorry username exists";
			
		}
		
	}
	
	//create route
	@RequestMapping(value = "/createRoute/{seats}/{start}/{end}/{date}/{vehicle}/{driver}/{price}", method = RequestMethod.POST)
	@ResponseBody
	public String createRoute(@PathVariable Integer seats, @PathVariable String start,
			@PathVariable String end, @PathVariable String date, @PathVariable String vehicle,
			@PathVariable String driver, @PathVariable String price) {
		

		Route route = repository.createRoute(seats,start,end,date,vehicle, driver, price);
			
		if (route != null) {
			return " route created!";
		} else {
			return "route not created.";
		}
		
	}
	
	//find route
	@RequestMapping(value = "/findRoute/{date}/{startCity}/{endCity}", method = RequestMethod.GET)
	@ResponseBody
	public String findRoute(@PathVariable String date, @PathVariable String startCity, @PathVariable String endCity) {

		List<Route>routes = repository.findRoutes(date, startCity, endCity);
			
		if (routes.isEmpty() != true) {
			
			String list = "";
			
			for (Route route : routes ) {
			
				list += route.getDriver();
				
			}
			
			return list;
			
		} else {
			return "route not found.";
		}
		
	}
	
	//join route
	@RequestMapping(value = "/joinRoute/{id}/{passenger}", method = RequestMethod.GET)
	@ResponseBody
	public String joinRoute(@PathVariable long id, @PathVariable String passenger) {
		
		Route route = repository.joinRoute(id, passenger);
		
		if (route != null) {
			return " joined!";
		} else {
			return " not joined.";
		}
		
	}
	
	//end route
	@RequestMapping(value = "/endRoute/{id}", method = RequestMethod.GET)
	@ResponseBody
	public String endRoute(@PathVariable long id) {
		
		Route route = repository.endRoute(id);
		
		if (route != null) {
			return " joined!";
		} else {
			return " not joined.";
		}
		
	}
	
	//show drivers routes
	@RequestMapping(value = "/showDriversRoutes/{driver}", method = RequestMethod.GET)
	@ResponseBody
	public String showDriversRoutes(@PathVariable String driver) {
		
		List<Route> routes = repository.showDriversRoutes(driver);
		
		if (routes.isEmpty() != true) {
			
			String list = "";
			
			for (Route route : routes) {
				
				list += route.getPrice();
				
			}
			
			return list;
			
		} else {
			return " not found.";
		}
		
	}
	
	//show passengers routes
	@RequestMapping(value = "/showPassengersRoutes/{passenger}", method = RequestMethod.GET)
	@ResponseBody
	public String showPassengersRoutes(@PathVariable String passenger) {
		
		List<Route> routes = repository.showPassengerRoutes(passenger);
		
		if (routes.isEmpty() != true) {
			
			String list = "";
			
			for (Route route : routes) {
				
				list += route.getPrice();
				
			}
			
			return list;
			
		} else {
			
			return " not found.";
		}
		
	}
	
	//leave route
	@RequestMapping(value = "/leaveRoute/{id}/{passenger}", method = RequestMethod.GET)
	@ResponseBody
	public String leaveRoute(@PathVariable long id, @PathVariable String passenger) {
		
		Route route = repository.leaveRoute(id, passenger);
		
		if (route != null) {
			
			return "removed";
			
		} else {
			
			return " not in this route.";
		}
		
	}
	
	//leave route
	@RequestMapping(value = "/rateUser/{username}/{rating}", method = RequestMethod.GET)
	@ResponseBody
	public String rateUser(@PathVariable String username, @PathVariable int rating) {
			
		User user = repository.rateUser(username, rating);
			
		if (user != null) {
				
			return "updated";
				
		} else {
				
			return " not found";
		}
			
	}
	
	//create route
	@RequestMapping(value = "/modifyRoute/{seats}/{start}/{end}/{date}/{vehicle}/{price}/{id}", method = RequestMethod.GET)
	@ResponseBody
	public String modifyRoute(@PathVariable Integer seats, @PathVariable String start,
		@PathVariable String end, @PathVariable String date, @PathVariable String vehicle,
		@PathVariable String price, @PathVariable long id) {
			
		Route route = repository.modifyRoute(seats,start,end,date,vehicle, price, id);
				
		if (route != null) {
			return "modified!";
		} else {
			return "not found.";
		}
			
	}
	
}