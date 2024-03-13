package foodapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import foodapp.model.Dish;
import foodapp.model.Restaurant;
import foodapp.repo.Dish_Repo;

@Service
public class Dish_ServicesImpl implements Dish_Services {

	@Autowired
	private Dish_Repo dr;
	@Override
	public Dish post(Dish d) {
		return dr.save(d);
	}

	@Override
	public Optional<Dish> delete(int id) {
		Optional<Dish> d=dr.findById(id);
		if(dr.existsById(id)) {
			dr.deleteById(id);
			return d;
		}
		return null;
	}

	@Override
	public List<Dish> get() {
		return dr.findAll();
		
	}

	@Override
	public Dish put(Dish d) {
		return dr.save(d);
	}

	@Override
	public Optional<Dish> getbyid(int id) {
		if(dr.existsById(id)) {
			return dr.findById(id);
		}
		return null;
	}

	public List<Dish> getall(int restaurantId) {
		return dr.findAll();
	}

	@Override
	public List<Dish> getAllDishesForRestaurant(int restaurantId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
    public Optional<Dish> getDishByRestaurantAndId(int restaurantId, int dishId) {
		return null;
    }

}
