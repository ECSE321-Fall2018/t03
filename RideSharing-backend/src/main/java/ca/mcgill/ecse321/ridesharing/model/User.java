package ca.mcgill.ecse321.ridesharing.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User{
private String username;
   
   public void setUsername(String value) {
this.username = value;
    }
@Id
public String getUsername() {
return this.username;
    }
private boolean isActive;

public void setIsActive(boolean value) {
   this.isActive = value;
}

public boolean isIsActive() {
   return this.isActive;
}

private String firstName;

public void setFirstName(String value) {
this.firstName = value;
    }
public String getFirstName() {
return this.firstName;
    }
private String lastName;

public void setLastName(String value) {
this.lastName = value;
    }
public String getLastName() {
return this.lastName;
    }
private String password;

public void setPassword(String value) {
this.password = value;
    }
public String getPassword() {
return this.password;
       }
   }
