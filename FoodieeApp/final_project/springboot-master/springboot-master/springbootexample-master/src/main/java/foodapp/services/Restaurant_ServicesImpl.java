package foodapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import foodapp.model.Dish;
import foodapp.model.Restaurant;
import foodapp.repo.Restaurant_Repo;

@Service
public class Restaurant_ServicesImpl implements Restaurant_Services{

	@Autowired
	private Restaurant_Repo rr;
	@Override
	public Restaurant post(Restaurant r) {
		return rr.save(r);
	}

	@Override
	public List<Restaurant> getall() {
		return rr.findAll();
	}

	@Override
	public Restaurant put(Restaurant r) {
		return rr.save(r);
	}

	@Override
	public Optional<Restaurant> getbyid(int id) {
		if(rr.existsById(id)) {
			return rr.findById(id);
		}
		return null;
	}

	@Override
	public Optional<Restaurant> delete(int id) {
		Optional<Restaurant> r=rr.findById(id);
		if(rr.existsById(id)) {
			rr.deleteById(id);
			return r;
		}
		return null;
	}

	 

	}

	

