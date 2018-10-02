package ca.mcgill.ecse321.ridesharing.model;

import javax.persistence.Entity;
import ca.mcgill.ecse321.ridesharing.model.User;
import java.util.Set;
import javax.persistence.ManyToMany;

@Entity
public class Role{
private Set<User> user;

@ManyToMany(mappedBy="role")
public Set<User> getUser() {
   return this.user;
}

public void setUser(Set<User> users) {
   this.user = users;
}

}