package foodapp.services;
import java.util.List;
import java.util.Optional;

import foodapp.model.Dish;

public interface Dish_Services {
    public Dish post(Dish d);
    public Optional<Dish> delete(int id);
    public List<Dish> get();
    public Dish put(Dish d);
    public Optional<Dish> getbyid(int id);
    public List<Dish> getAllDishesForRestaurant(int restaurantId);
    public Optional<Dish> getDishByRestaurantAndId(int restaurantId, int dishId);
}
