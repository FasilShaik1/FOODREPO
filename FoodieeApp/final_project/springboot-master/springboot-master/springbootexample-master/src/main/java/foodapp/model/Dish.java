
package foodapp.model;

import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;

@Entity

@Table(name = "dish")

public class Dish {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int dish_id;

	@Column(nullable = false)

	private String dish_name;

	@Column(nullable = false)

	private int dish_price;

	@Column(nullable = false)

	private int dish_rating;

	@Column(nullable = false)

	private int restaurant_id;

	public Dish() {

	}

	public Dish(int dish_id, String dish_name, int dish_price, int dish_rating, int restaurant_id) {

		super();

		this.dish_id = dish_id;

		this.dish_name = dish_name;

		this.dish_price = dish_price;

		this.dish_rating = dish_rating;

		this.restaurant_id = restaurant_id;

	}

	public int getDish_id() {

		return dish_id;

	}

	public void setDish_id(int dish_id) {

		this.dish_id = dish_id;

	}

	public String getDish_name() {

		return dish_name;

	}

	public void setDish_name(String dish_name) {

		this.dish_name = dish_name;

	}

	public int getDish_price() {

		return dish_price;

	}

	public void setDish_price(int dish_price) {

		this.dish_price = dish_price;

	}

	public int getDish_rating() {

		return dish_rating;

	}

	public void setDish_rating(int dish_rating) {

		this.dish_rating = dish_rating;

	}

	public int getRestaurant_id() {

		return restaurant_id;

	}

	public void setRestaurant_id(int restaurant_id) {

		this.restaurant_id = restaurant_id;

	}

	@Override

	public String toString() {

		return "Dish [dish_id=" + dish_id + ", dish_name=" + dish_name + ", dish_price=" + dish_price

				+ ", dish_rating=" + dish_rating + ", restaurant_id=" + restaurant_id + "]";

	}



}