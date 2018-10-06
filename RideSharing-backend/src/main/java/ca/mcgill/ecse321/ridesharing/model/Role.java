package ca.mcgill.ecse321.ridesharing.model;

import javax.persistence.Entity;
import ca.mcgill.ecse321.ridesharing.model.User;
import java.util.List;
import javax.persistence.ManyToMany;
import javax.persistence.Id;

@Entity
public class Role{
private List<User> user;
@Id
@ManyToMany(mappedBy="role")
public List<User> getUser() {
   return this.user;
}

public void setUser(List<User> users) {
   this.user = users;
}

}