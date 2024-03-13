package foodapp.controller;
 
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
 
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import foodapp.model.Dish;
import foodapp.model.Restaurant;
import foodapp.repo.Restaurant_Repo;
import foodapp.services.Dish_ServicesImpl;
import foodapp.services.Restaurant_ServicesImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
 
@RestController
@RequestMapping("api/v1/restaurant/dish")
public class Dish_Controller implements WebMvcConfigurer {
    @Autowired
    private Dish_ServicesImpl ds;
    @Autowired
    private Restaurant_Repo rr;
    @Autowired
    private Restaurant_ServicesImpl rs;
 
    @Transactional
    @PostMapping(value = "admin/post", consumes = {"application/json", "application/xml", "application/yaml", "text/csv", "application/javascript"})
    public ResponseEntity<?> post(@RequestBody Dish d) {
        Optional<Restaurant> restaurant = rr.findById(d.getRestaurant_id());
        if (!restaurant.isPresent()) {
            return new ResponseEntity<String>("Restaurant with id " + d.getRestaurant_id() + " not found", HttpStatus.NOT_FOUND);
        }
 
        List<Dish> d1 = ds.get();
        Dish d2 = ds.post(d);
        if (d1.contains(d2)) {
            return new ResponseEntity<String>("Same dish id exists already", HttpStatus.CONFLICT);
        }
 
        return new ResponseEntity<Dish>(ds.post(d), HttpStatus.CREATED);
    }
 
    @GetMapping(value = "admin/get", produces = {"application/json", "application/xml", "application/yaml", "text/csv", "application/javascript"})
    public ResponseEntity<?> getall() {
        return new ResponseEntity<List<Dish>>(ds.get(), HttpStatus.OK);
    }
    
    @GetMapping(value = "user/get", produces = {"application/json", "application/xml", "application/yaml", "text/csv", "application/javascript"})
    public ResponseEntity<?> getall1() {
        return new ResponseEntity<List<Dish>>(ds.get(), HttpStatus.OK);
    }
 
    @PutMapping(value = "admin/put/{id}", consumes = {"application/json", "application/xml", "application/yaml", "text/csv", "application/javascript"})
    public ResponseEntity<?> put(@PathVariable("id") int id, @RequestBody Dish updatedDish) {
        Optional<Dish> existingDishOptional = ds.getbyid(id);
        if (!existingDishOptional.isPresent()) {
            return new ResponseEntity<String>("No dish found with id: " + id, HttpStatus.NOT_FOUND);
        }

        Dish existingDish = existingDishOptional.get();
        // Update the attributes of the existing dish with the attributes of the updated dish
        existingDish.setDish_name(updatedDish.getDish_name());
        existingDish.setDish_price(updatedDish.getDish_price());
        existingDish.setDish_rating(updatedDish.getDish_rating());
        existingDish.setRestaurant_id(updatedDish.getRestaurant_id());

        // Save the updated dish
        Dish updatedDishEntity = ds.put(existingDish);
        return new ResponseEntity<Dish>(updatedDishEntity, HttpStatus.OK);
    }

    
    @GetMapping(value = "user/get/{id}", produces = {"application/json", "application/xml", "application/yaml", "text/csv", "application/javascript"})
    public ResponseEntity<?> getbyid(@PathVariable("id") int id) {
        Optional<Dish> d = ds.getbyid(id);
        if (d == null) {
            return new ResponseEntity<String>("no such dish id to get", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Optional<Dish>>(d, HttpStatus.OK);
    }

    @GetMapping(value = "/restaurant/{restaurantId}/dishes", produces = {"application/json", "application/xml", "application/yaml", "text/csv", "application/javascript"})
    public ResponseEntity<?> getDishesByRestaurantId(@PathVariable("restaurantId") int restaurantId) {
        List<Dish> allDishes = ds.getall(restaurantId); 
        List<Dish> dishesFromRestaurant = new ArrayList<>();

                for (Dish dish : allDishes) {
            if (dish.getRestaurant_id() == restaurantId) {
                dishesFromRestaurant.add(dish);
            }
        }

        if (dishesFromRestaurant.isEmpty()) {
            return new ResponseEntity<String>("No dishes found for the restaurant with id: " + restaurantId, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Dish>>(dishesFromRestaurant, HttpStatus.OK);
    }


    @GetMapping(value = "/restaurant/{restaurantId}/dish/{dishId}", produces = {"application/json", "application/xml", "application/yaml", "text/csv", "application/javascript"})
    public ResponseEntity<?> getDishByRestaurantAndId(@PathVariable("restaurantId") int restaurantId, @PathVariable("dishId") int dishId) {
        // Implement logic directly in the controller
        Optional<Dish> dishOptional = ds.getbyid(dishId);
        
        if (!dishOptional.isPresent() || dishOptional.get().getRestaurant_id() != restaurantId) {
            return new ResponseEntity<String>("No dish found with id: " + dishId + " for the restaurant with id: " + restaurantId, HttpStatus.NOT_FOUND);
        }
        Dish dish = dishOptional.get();
        return new ResponseEntity<Dish>(dish, HttpStatus.OK);
    }
    
    @PostMapping(value = "/restaurant/dishes/{restaurantId}", consumes = {"application/json", "application/xml", "application/yaml", "text/csv", "application/javascript"})
    public ResponseEntity<?> addDishByRestaurantId(@PathVariable("restaurantId") int restaurantId, @RequestBody Map<String, Object> requestBody) {
        if (!requestBody.containsKey("dish_name") || !requestBody.containsKey("dish_price") || !requestBody.containsKey("dish_rating")) {
            return new ResponseEntity<String>("Required fields 'dish_name', 'dish_price', and 'dish_rating' are missing in the request body", HttpStatus.BAD_REQUEST);
        }

        Optional<Restaurant> restaurantOptional = rr.findById(restaurantId);
        if (!restaurantOptional.isPresent()) {
            return new ResponseEntity<String>("Restaurant with id " + restaurantId + " not found", HttpStatus.NOT_FOUND);
        }
        
        String dishName = (String) requestBody.get("dish_name");
        int dishPrice = (int) requestBody.get("dish_price");
        int dishRating = (int) requestBody.get("dish_rating");

        Dish newDish = new Dish();
        newDish.setDish_name(dishName);
        newDish.setDish_price(dishPrice);
        newDish.setDish_rating(dishRating);
        newDish.setRestaurant_id(restaurantId);

        // Add the new dish to the database
        Dish addedDish = ds.post(newDish);
        return new ResponseEntity<Dish>(addedDish, HttpStatus.CREATED);
    }







 
    @DeleteMapping(value = "admin/delete/{id}", produces = {"application/json", "application/xml", "application/yaml", "text/csv", "application/javascript"})
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        Optional<Dish> d = ds.delete(id);
        if (d == null) {
            return new ResponseEntity<String>("no such dish id to delete", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Optional<Dish>>(d, HttpStatus.OK);
    }
 
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        converters.add(new MappingJackson2HttpMessageConverter(objectMapper));
        converters.add(new MappingJackson2XmlHttpMessageConverter());
        converters.add(new MappingJackson2HttpMessageConverter(objectMapper));
        converters.add(new MappingJackson2JavascriptHttpMessageConverter());
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema schema = csvMapper.schemaFor(Dish.class).withHeader();
        converters.add(new MappingJackson2HttpMessageConverter(csvMapper));
        converters.add(new MappingJackson2YamlHttpMessageConverter(objectMapper));
    }
 
    private class MappingJackson2YamlHttpMessageConverter extends MappingJackson2HttpMessageConverter {
        public MappingJackson2YamlHttpMessageConverter(ObjectMapper objectMapper) {
            super(objectMapper);
            this.setSupportedMediaTypes(Collections.singletonList(MediaType.valueOf("application/yaml")));
        }
    }
 
    private class MappingJackson2JavascriptHttpMessageConverter extends MappingJackson2HttpMessageConverter {
        public MappingJackson2JavascriptHttpMessageConverter() {
            super();
            this.setSupportedMediaTypes(Collections.singletonList(MediaType.valueOf("application/javascript")));
        }
    }
}