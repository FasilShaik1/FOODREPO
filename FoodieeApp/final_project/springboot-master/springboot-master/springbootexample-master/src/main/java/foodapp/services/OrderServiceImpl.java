package foodapp.services;
 
import java.util.List;
import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import foodapp.model.Order;
import foodapp.repo.Order_Repo;
 
@Service
public class OrderServiceImpl implements OrderService {
 
    @Autowired
    private Order_Repo orderRepository;
 
    @Override
    public Order placeOrder(Order order) throws Exception {
        return orderRepository.save(order);
    }
 
    @Override
    public List<Order> getOrdersByUserId(int userId) throws Exception {
        return orderRepository.findAllByUserId(userId);
    }
 
    @Override
    public Order getOrderById(int orderId) throws Exception {
        return orderRepository.findById(orderId).orElseThrow(() -> new Exception("Order not found"));
    }
    
    @Override
    public Order updateOrderQuantity(int orderId, int newQuantity) throws Exception {
        // Get the order from the repository
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        Order order = optionalOrder.orElseThrow(() -> new Exception("Order not found with ID: " + orderId));
        
        // Update the quantity
        order.setQuantity(newQuantity);
        
        // Save the updated order
        return orderRepository.save(order);
    }
    @Override
    public void deleteOrder(int orderId) throws Exception {
        orderRepository.deleteById(orderId);
    }
    @Override
    public List<Order> getOrdersByRestaurantId(int restaurantId) throws Exception {
        return null;
    }
    
}
 
