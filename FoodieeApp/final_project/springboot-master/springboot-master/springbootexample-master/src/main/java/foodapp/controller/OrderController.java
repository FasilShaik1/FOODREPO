package foodapp.controller;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import foodapp.model.Dish;
import foodapp.model.Order;
import foodapp.model.OrderRequest;
import foodapp.model.Restaurant;
import foodapp.model.User;
import foodapp.services.Dish_Services;
import foodapp.services.OrderService;
import foodapp.services.Restaurant_Services;
import foodapp.services.UserService;
 
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
 
    @Autowired
    private OrderService orderService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private Dish_Services dishService;
    
    @Autowired
    private Restaurant_Services restaurantService;
    
    
    
    @PostMapping("/checkout/{userId}")
    public ResponseEntity<Map<String, Object>> checkout(@PathVariable int userId,
                                                        @RequestBody List<OrderRequest> orderRequests) throws Exception {
        Optional<User> optionalUser = userService.getUserById(userId);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            List<Order> placedOrders = new ArrayList<>();
            double grandTotal = 0; 
            String trackingId = UUID.randomUUID().toString();

            for (OrderRequest orderRequest : orderRequests) {
                int dishId = orderRequest.getDishId();
                int quantity = orderRequest.getQuantity();

                Optional<Dish> optionalDish = dishService.getbyid(dishId);

                if (optionalDish.isPresent()) {
                    Dish dish = optionalDish.get();
                    double dishPrice = dish.getDish_price();
                    int restaurantId = dish.getRestaurant_id();

                    Optional<Restaurant> optionalRestaurant = restaurantService.getbyid(restaurantId);

                    if (optionalRestaurant.isPresent()) {
                        Restaurant restaurant = optionalRestaurant.get();

                        Order order = new Order(user, restaurant, dish, quantity, dishPrice * quantity);
                        order.setTrackingId(trackingId);

                        grandTotal += dishPrice * quantity;

                        Order placedOrder = orderService.placeOrder(order);
                        placedOrders.add(placedOrder);
                    } else {
                        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                    }
                } else {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            }

            Map<String, Object> response = new HashMap<>();
            response.put("placedOrders", placedOrders);
            response.put("grandTotal", grandTotal);
            response.put("trackingId", trackingId);

            return ResponseEntity.ok().body(response);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    
    
   
 
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> getOrdersByUserId(@PathVariable int userId) throws Exception {
        List<Order> orders = orderService.getOrdersByUserId(userId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
 
    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable int orderId) throws Exception {
        Order order = orderService.getOrderById(orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
    @PutMapping("/{orderId}/quantity")
    public ResponseEntity<Order> updateOrderQuantity(
            @PathVariable int orderId,
            @RequestParam("quantity") int newQuantity) throws Exception {
        Order updatedOrder = orderService.updateOrderQuantity(orderId, newQuantity);
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }
    
    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Order>> getOrdersByRestaurantId(@PathVariable int restaurantId) {
        try {
           
            List<Order> orders = orderService.getOrdersByRestaurantId(restaurantId);
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable int orderId) throws Exception {
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }
 
}