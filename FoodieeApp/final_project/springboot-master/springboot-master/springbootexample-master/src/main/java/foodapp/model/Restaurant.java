package foodapp.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.*;

@Entity
@Table(name="restaurant")
public class Restaurant {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int restaurant_id;
	@Column(nullable=false)
	private String restaurant_name;
	@Column(nullable=false)
	private String restaurant_cat;
	
	@Column(nullable=false)
	private String restaurant_loc;
	@Column(nullable=false)
	private String restaurant_contact;
	

	public Restaurant() {
		
	}
	public Restaurant(int restaurant_id, String restaurant_name, String restaurant_cat, String restaurant_loc,
			String restaurant_contact) {
		super();
		this.restaurant_id = restaurant_id;
		this.restaurant_name = restaurant_name;
		this.restaurant_cat = restaurant_cat;
		this.restaurant_loc = restaurant_loc;
		this.restaurant_contact = restaurant_contact;
	}
	
	public int getRestaurant_id() {
		return restaurant_id;
	}
	public void setRestaurant_id(int restaurant_id) {
		this.restaurant_id = restaurant_id;
	}
	public String getRestaurant_name() {
		return restaurant_name;
	}
	public void setRestaurant_name(String restaurant_name) {
		this.restaurant_name = restaurant_name;
	}
	public String getRestaurant_cat() {
		return restaurant_cat;
	}
	public void setRestaurant_cat(String restaurant_cat) {
		this.restaurant_cat = restaurant_cat;
	}
	@Override
	public String toString() {
		return "Restaurant [restaurant_id=" + restaurant_id + ", restaurant_name=" + restaurant_name
				+ ", restaurant_cat=" + restaurant_cat + ", restaurant_loc=" + restaurant_loc + ", restaurant_contact="
				+ restaurant_contact + "]";
	}
	public String getRestaurant_loc() {
		return restaurant_loc;
	}
	public void setRestaurant_loc(String restaurant_loc) {
		this.restaurant_loc = restaurant_loc;
	}
	public String getRestaurant_contact() {
		return restaurant_contact;
	}
	public void setRestaurant_contact(String restaurant_contact) {
		this.restaurant_contact = restaurant_contact;
	}


}
