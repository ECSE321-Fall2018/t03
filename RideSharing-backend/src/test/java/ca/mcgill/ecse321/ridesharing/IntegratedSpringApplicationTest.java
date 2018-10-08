package ca.mcgill.ecse321.ridesharing;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.*;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class IntegratedSpringApplicationTest{
	
	@Before
	public void setup()
	{
		
		String url="jdbc:postgresql://ec2-23-23-216-40.compute-1.amazonaws.com:5432/ddp4sc0fffl2n9";
    	String usernameDB= "xhboobjzljpdus";
    	String passwordDB="03d06a487d48508fd9476509db46d2644a17b36fe32be0d2c5d411f8dd5f3226";
    	
    	Connection conn = null;
    	Statement stmt = null;
        ResultSet rs = null;
        
		
        String tempq = "INSERT INTO users(rating, username, password) VALUES ('";
        
        String tmprate = String.valueOf(5);
        
        String rating = tmprate + "',";
        String user = "\'"+"test" + "\',";
        String pass = "\'"+"password" +"\');";
        
        String query1 = tempq+rating+user+pass;
  
        String tempq2 = "INSERT INTO users(rating, username, password, email) VALUES (";
        
        String rating2 =  "5,";
        String user2 = "\'"+"Benji" + "\',";
        String pass2 = "\'"+"Szwimer" +"\',";
        String email2 = "\'"+"Hi@mail.com" +"\');";
        
        String query2 = tempq2+rating2+user2+pass2+email2;
        
        String price = "200" + ",";
        String date ="'" +"2018-01-01" + "',";
        String startCity = "\'"+"Montreal" +"\',";
        String endCity = "\'"+"Mars" +"\',";
        String numSeats = "4"+");";
        String isAvailable =  "TRUE,";
        String isDriver = "TRUE,";
        String ID_users = "2,";
        
        String query3 = tempq+ID_users+price+startCity+endCity+isAvailable+isDriver+date+numSeats;
        
        String tempq4 = "INSERT INTO users(rating, username, password, email) VALUES (";
        
        String rating4 =  "4,";
        String user4 = "\'"+"Ethan" + "\',";
        String pass4 = "\'"+"Itovitch" +"\',";
        String email4 = "\'"+"Eth@mail.com" +"\');";
        
        String query4 = tempq4+rating4+user4+pass4+email4;
        
        
        try {
			conn = DriverManager.getConnection(url, usernameDB, passwordDB);
		} catch (SQLException e1) {
	
			e1.printStackTrace();
		}
        
        try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
        try {
			rs = stmt.executeQuery(query1);
			rs = stmt.executeQuery(query2);
			rs = stmt.executeQuery(query3);
			rs = stmt.executeQuery(query4);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSignUpPassenger(){
		String url="jdbc:postgresql://ec2-23-23-216-40.compute-1.amazonaws.com:5432/ddp4sc0fffl2n9";
    	String usernameDB= "xhboobjzljpdus";
    	String passwordDB="03d06a487d48508fd9476509db46d2644a17b36fe32be0d2c5d411f8dd5f3226";
		int rating; 
		String username; 
		String password; 
	 	Connection conn = null;
    	Statement stmt = null;
        ResultSet rs = null;
		String tempq = "select * from users WHERE username like 'test'; ";
		
		
		 try {
			conn = DriverManager.getConnection(url, usernameDB, passwordDB);
			
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
				//rs = stmt.executeQuery(query);
	        	rs = stmt.executeQuery(tempq);
		        rating = rs.getInt("rating");
		        System.out.println(rating);
		        username =rs.getString("username");
		        System.out.println(username);
		        password =rs.getString("password");
		        System.out.println(password);
		        
		        assertEquals(5, rating);
		        assertEquals("test", username);
		        assertEquals("password", password);
		
	        }
	        
	        catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
	        }
	        
	        
	       
	}
	
	@Test
	public void testSignUpDriver(){
		String url="jdbc:postgresql://ec2-23-23-216-40.compute-1.amazonaws.com:5432/ddp4sc0fffl2n9";
    	String usernameDB= "xhboobjzljpdus";
    	String passwordDB="03d06a487d48508fd9476509db46d2644a17b36fe32be0d2c5d411f8dd5f3226";
		int rating; 
		String username; 
		String password;
		String email;
	 	Connection conn = null;
    	Statement stmt = null;
        ResultSet rs = null;
		String tempq = "select * from users WHERE username like 'test'; ";
		
		
		 try {
			conn = DriverManager.getConnection(url, usernameDB, passwordDB);
			
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
	        	
				//rs = stmt.executeQuery(query);
	        	rs = stmt.executeQuery(tempq);
		        rating = rs.getInt("rating");
		        username =rs.getString("username");
		        password =rs.getString("password");
		        email =rs.getString("email");
		        
		        assertEquals(5, rating);
		        assertEquals("Benji", username);
		        assertEquals("Szwimer", password);
		        assertEquals("Hi@mail.com", password);
		
	        }
	        
	        catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
	        }
	        
	        
	       
	}
	
	@Test
	public void testCreateRouteFindRoute(){
		String url="jdbc:postgresql://ec2-23-23-216-40.compute-1.amazonaws.com:5432/ddp4sc0fffl2n9";
    	String usernameDB= "xhboobjzljpdus";
    	String passwordDB="03d06a487d48508fd9476509db46d2644a17b36fe32be0d2c5d411f8dd5f3226";
		int price; 
		Date date; 
		String startCity;
		String endCity;
		int numSeats;
		boolean isAvailable;
		boolean isDriver;
	 	Connection conn = null;
    	Statement stmt = null;
        ResultSet rs = null;
		String tempqSE = "select * from users WHERE username like 'test'; ";
		
		
		 try {
			conn = DriverManager.getConnection(url, usernameDB, passwordDB);
			
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
	        	
	        	
	        	
				//rs = stmt.executeQuery(query);
	        	rs = stmt.executeQuery(tempqSE);
		        price = rs.getInt("price");
		        date =rs.getDate("date");
		        startCity =rs.getString("startcity");
		        endCity =rs.getString("endcity");
		        numSeats =rs.getInt("numofseats");
		        isAvailable = rs.getBoolean("isdriver");
		        isDriver = rs.getBoolean("isavailable");
		        
		        assertEquals(200, price);
		        assertEquals("2018-01-01", date);
		        assertEquals("Montreal", startCity);
		        assertEquals("Mars", endCity);
		        assertEquals(4, numSeats);
		        assertEquals(true, isAvailable);
		        assertEquals(true, isDriver);
		
	        }
	        
	        catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
	        }
	        
	        
	       
	}
	
	@Test
	public void testRateDriver(){
		String url="jdbc:postgresql://ec2-23-23-216-40.compute-1.amazonaws.com:5432/ddp4sc0fffl2n9";
    	String usernameDB= "xhboobjzljpdus";
    	String passwordDB="03d06a487d48508fd9476509db46d2644a17b36fe32be0d2c5d411f8dd5f3226";
		int rating;
	 	Connection conn = null;
    	Statement stmt = null;
        ResultSet rs = null;
        String tempqUP = "UPDATE users SET rating="+"5"+" WHERE username like '"+"Ethan"+"';";
		String tempqSE = "select * from users WHERE username like 'Ethan'; ";
		
		
		 try {
			conn = DriverManager.getConnection(url, usernameDB, passwordDB);
			
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

	        	rs = stmt.executeQuery(tempqUP);
	        	rs = stmt.executeQuery(tempqSE);
		        rating = rs.getInt("rating");
		        
		        assertEquals(5, rating);
		
	        }
	        
	        catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
	        }
	        
	        
	       
	}
	
	@After
	public void cleanUp() {
		
		String url="jdbc:postgresql://ec2-23-23-216-40.compute-1.amazonaws.com:5432/ddp4sc0fffl2n9";
    	String usernameDB= "xhboobjzljpdus";
    	String passwordDB="03d06a487d48508fd9476509db46d2644a17b36fe32be0d2c5d411f8dd5f3226";
    	Connection conn = null;
    	Statement stmt = null;
        ResultSet rs = null;
        String tempqUP1 = "DELETE FROM users WHERE username = test;";
        String tempqUP2 = "DELETE FROM users WHERE username = Benji;";
        String tempqUP3 = "DELETE FROM users WHERE username = Ethan;";
        String tempqUP4 = "DELETE FROM destination WHERE endcity = Mars;";
        
        
        try {
			conn = DriverManager.getConnection(url, usernameDB, passwordDB);
			
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

	        	rs = stmt.executeQuery(tempqUP1);
	        	//rs = stmt.executeQuery(tempqUP2);
	        	//rs = stmt.executeQuery(tempqUP3);
	        	//rs = stmt.executeQuery(tempqUP4);
		
	        }
	        
	        catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
	        }
		
	}
	

}