 
package foodapp.services;
 
import java.util.List;
 
import foodapp.model.Order;
 
public interface OrderService {
 
    Order placeOrder(Order order) throws Exception;
 
    List<Order> getOrdersByUserId(int userId) throws Exception;
 
    Order getOrderById(int orderId) throws Exception;
    
    Order updateOrderQuantity(int orderId, int newQuantity) throws Exception;
    
    void deleteOrder(int orderId) throws Exception;
    List<Order> getOrdersByRestaurantId(int restaurantId) throws Exception;
}
 
 