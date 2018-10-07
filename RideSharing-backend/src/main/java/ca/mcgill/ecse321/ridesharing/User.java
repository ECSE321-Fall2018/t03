package ca.mcgill.ecse321.ridesharing;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User{
private String username;
private String password;
private int rating;
private boolean isActive;
   
public void setUsername(String value) {
this.username = value;
    }

public String getUsername() {
return this.username;
    }


public void setIsActive(boolean value) {
   this.isActive = value;
}

public boolean isIsActive() {
   return this.isActive;
}

public void setPassword(String value) {
this.password = value;
    }
public String getPassword() {
return this.password;
       }
public int getRating() {
	return rating;
}
public void setRating(int rating) {
	this.rating = rating;
}
   }
