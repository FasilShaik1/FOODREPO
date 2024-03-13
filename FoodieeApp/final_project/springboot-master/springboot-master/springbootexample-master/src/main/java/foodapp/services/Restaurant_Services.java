package foodapp.services;

import java.util.List;
import java.util.Optional;

import foodapp.model.Dish;
import foodapp.model.Restaurant;

public interface Restaurant_Services {
	public Restaurant post(Restaurant r);
	public List<Restaurant> getall();
	public Restaurant put(Restaurant r);
	public Optional<Restaurant> getbyid(int id);
	public Optional<Restaurant> delete(int id);
	 

}
