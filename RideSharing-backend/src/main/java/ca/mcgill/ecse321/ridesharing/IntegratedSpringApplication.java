package ca.mcgill.ecse321.ridesharing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.JndiConnectionFactoryAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;



//@EnableAutoConfiguration
//@SpringBootApplication

@EnableAutoConfiguration(exclude = {JndiConnectionFactoryAutoConfiguration.class,DataSourceAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class,JpaRepositoriesAutoConfiguration.class,DataSourceTransactionManagerAutoConfiguration.class})

@RestController
public class IntegratedSpringApplication extends SpringBootServletInitializer{
	
//	private final RideSharingRepository repository;
//	
//	public IntegratedSpringApplication(RideSharingRepository repository) {
//    	this.repository = repository;
//    }
 
     @Override
        protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
            return application.sources(IntegratedSpringApplication.class);
        }
 
     
    public static void main(String[] args) {
    	
        SpringApplication.run(IntegratedSpringApplication.class, args);
        //RideSharingController rideSharingController = new RideSharingController();
        
        
      
       
    	}
        //@RequestMapping("/")
    	//public String greeting() {
    	//	return "<h1>Welcome to the ride sharing app!</h1>";
    
    
    

    
    
   
    @RequestMapping("/")
	public String greeting() {
		return "<h1>Welcome to the ride sharing app!</h1>"
				+ "<input type=\"submit\" value=\"Im a pass\" \n" + 
				"    onclick=\"window.location='/PassSignUp';\" /> ";
	}
    
    @RequestMapping("/PassSignUp")
    public String passSignUp() {
    	
    	return 	
//    			
    			
    			"<form action=\"http://localhost:8080/PassSignUp/Destination\" method=\"get\">\n" + 
    			"  <div>\n" + 
    			"    <label for=\"username\">Username</label>\n" + 
    			"    <input name=\"username\" id=\"username\" value=\"\">\n" + 
    			"  </div>\n" + 
    			"  <div>\n" + 
    			"    <label for=\"password\">Password</label>\n" + 
    			"    <input name=\"password\" id=\"password\" value=\"\">\n" + 
    			"  </div>\n" + 
    			"  <div>\n" + 
    			"    <button>Sign in/Sign up</button>\n" + 
    			"  </div>\n" + 
    			"</form>";
    			
  
    	

    	
    }
    
    @RequestMapping("/Destination")
    @ResponseBody
    public String dest() {
        return "hey";
    }
    
//    @PostMapping("/PassSignUp/Destination")
//    public String destinationPassenger() {
//    	
//    	String url="jdbc:postgresql://ec2-23-23-216-40.compute-1.amazonaws.com:5432/ddp4sc0fffl2n9";
//    	String username= "xhboobjzljpdus";
//    	String password="03d06a487d48508fd9476509db46d2644a17b36fe32be0d2c5d411f8dd5f3226";
//    	
//    	Connection conn = null;
//    	Statement stmt = null;
//        ResultSet rs = null;
//        
//        String tempq = "INSERT INTO users(rating, username, password) VALUES(";
//                
//        String rating = "4,";
//        String user = "'Whoever',";
//        String pass = "'placeholder');";
//        
//        String query = tempq+rating+user+pass;
//        
//        
//        try {
//			conn = DriverManager.getConnection(url, username, password);
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//        
//        try {
//			stmt = conn.createStatement();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        try {
//			rs = stmt.executeQuery(query);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//            
// 	    System.out.println(query); 
//        
//    	return "<h1>Success</h1>";
//    }
    
    @RequestMapping("/PassSignUp/{Destination}")
    public String getDetails(@RequestParam(value="username", required=true) String param1,@RequestParam(value="password", required=false) String param2){
		String status = insertInDB(5, param1, param2);
    	return "<h1>USER:"+ param1 + " </h1>\n"+
				"<h1>PASSWORD: "+param2+"</h1>" + 
    	"<h1>" +status+ "</h1>";
    	
    	}
    
    
    @RequestMapping("/passenger/{username}")
	public String createParticipant(@PathVariable String username) {
		//Passenger passenger = repository.createPassenger(username);
		return "hi"; //passenger.getUsername();
    	/*
    	return  "<form action=\"/action_page.php\" method=\"post\">\n" + 
    			"  username: <input type=\"text\" name=\"username\"><br>\n" + 
    			"  password: <input type=\"text\" name=\"password\"><br>\n" + 
    			"  <input type=\"submit\" value=\"Submit\">\n" + 
    			"</form>";
    			
    			<?php 
    			$db=pg_connect("host=localhost port=8080 dbname=postgres user=xhboobjzljpdus password= 03d06a487d48508fd9476509db46d2644a17b36fe32be0d2c5d411f8dd5f3226");
    			$query = "INSERT INTO users VALUES ('$_POST[username] ',' $_POST[password]')";
    			$result= pg_query($query);
    			?>
    			*/
	}
    
    @RequestMapping("driver/{username}")
    public String createDriver(@PathVariable String username) {
    	return "<form action=\"/action_page.php\" method=\"post\">\n" + 
    			"  username: <input type=\"text\" name=\"username\"><br>\n" + 
    			"  password: <input type=\"text\" name=\"password\"><br>\n" + 
    			"  email: <input type=\"text\" name=\"email\"><br>\n" + 
    			"  <input type=\"submit\" value=\"Submit\">\n" + 
    			"</form>";
    }
    @RequestMapping("admin/{username}")
    public String createAdmin(@PathVariable String username) {
    	return "<form action=\"/action_page.php\" method=\"post\">\n" + 
    			"  username: <input type=\"text\" name=\"username\"><br>\n" + 
    			"  password: <input type=\"text\" name=\"password\"><br>\n" + 
    			"  <input type=\"submit\" value=\"Submit\">\n" + 
    			"</form>";
    }
    @RequestMapping("driver/{username}/route")
    public String createRoute(@PathVariable String username) {
    	return "<form action=\"/action_page.php\" method=\"post\">\n" + 
    			"  Available Seats: <input type=\"text\" name=\"availableSeats\"><br>\n" + 
    			"  Start City: <input type=\"text\" name=\"sCity\"><br>\n" + 
    			"  End City: <input type=\"text\" name=\"eCity\"><br>\n" + 
    			"  Date: <input type=\"text\" name=\"date\"><br>\n" + 
    			"  Time: <input type=\"text\" name=\"time\"><br>\n" + 
    			"  <input type=\"submit\" value=\"Submit\">\n" + 
    			"</form>";
    }

    
    public String insertInDB(int _rating, String _user, String _pass)
    {
    	String url="jdbc:postgresql://ec2-23-23-216-40.compute-1.amazonaws.com:5432/ddp4sc0fffl2n9";
    	String username= "xhboobjzljpdus";
    	String password="03d06a487d48508fd9476509db46d2644a17b36fe32be0d2c5d411f8dd5f3226";
    	
    	Connection conn = null;
    	Statement stmt = null;
        ResultSet rs = null;
        
        String tempq = "INSERT INTO users(rating, username, password) VALUES ('";
            
        String tmprate = String.valueOf(_rating);
        
        String rating = tmprate + "',";
        String user = "\'"+_user + "\',";
        String pass = "\'"+_pass +"\');";
        
        
        
        String query = tempq+rating+user+pass;
        System.out.println(query);
        
        try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			rs = stmt.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return "Success";
    }



}
