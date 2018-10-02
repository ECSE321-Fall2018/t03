package ca.mcgill.ecse321.ridesharing.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Stop{
private String city;
   
   public void setCity(String value) {
this.city = value;
    }
public String getCity() {
return this.city;
    }
private Integer price;

public void setPrice(Integer value) {
this.price = value;
    }
public Integer getPrice() {
return this.price;
    }
private Route route;

@ManyToOne(optional=false)
public Route getRoute() {
   return this.route;
}

public void setRoute(Route route) {
   this.route = route;
}

}
