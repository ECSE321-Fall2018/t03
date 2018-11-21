package ca.mcgill.ecse321.ridesharing.model;

import javax.persistence.*;

@Entity
@Table(name = "participants")
/*
@NamedQueries({
	@NamedQuery(name = "User.findAll", query = "SELECT e FROM participants e")
})
*/
public class User{
private String username;
private String password;
private int rating;
private String type;
private int ridesTravelled;


public void setUsername(String value) {
this.username = value;
}

@Id
@Column(name = "username")
public String getUsername() {
return this.username;
}


public void setPassword(String value) {
this.password = value;
    }

@Column(name = "password")
public String getPassword() {
return this.password;
       }

public void setType(String type) {
this.type = type;
    }

@Column(name = "type")
public String getType() {
return this.type;
       }

@Column(name = "ridesTravelled")
public int getRidesTravelled() {
	return this.ridesTravelled;
}


public void setRidesTravelled(int value) {
	this.ridesTravelled = value;
}


@Column(name = "rating")
public int getRating() {
	return rating;
}


public void setRating(int rating) {
	this.rating = rating;
}
}
