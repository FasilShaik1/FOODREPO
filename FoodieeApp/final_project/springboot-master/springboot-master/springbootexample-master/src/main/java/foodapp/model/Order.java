 
package foodapp.model;
 
import java.util.Date;
 
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
 
@Entity
@Table(name = "orders")
public class Order {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
 
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;

 
 
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
 
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;
 
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dish_id", nullable = false)
    private Dish dish;
 
    @Column(nullable = false)
    private int quantity;
    
    @Column
    private double totalPrice;
    
    
    
    @Column(nullable = false, columnDefinition = "VARCHAR(255) DEFAULT ''")
    private String trackingId;

    
  
 public Order() {
	 
 }
    

	public String getTrackingId() {
		return trackingId;
	}

	public void setTrackingId(String trackingId) {
		this.trackingId = trackingId;
	}

	 @PrePersist
	    protected void onCreate() {
	        orderDate = new Date();
	    }
	 
	 
	  public Order(User user, Restaurant restaurant, Dish dish, int quantity, double totalPrice) {
	        this.user = user;
	        this.restaurant = restaurant;
	        this.dish = dish;
	        this.quantity = quantity;
	        this.totalPrice = totalPrice;
	    }
 
  

	public int getOrderId() {
        return orderId;
    }
 
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
 
    public Date getOrderDate() {
        return orderDate;
    }
 
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
 
    public User getUser() {
        return user;
    }
 
    public void setUser(User user) {
        this.user = user;
    }
 
    public Restaurant getRestaurant() {
        return restaurant;
    }
 
    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
 
    public Dish getDish() {
        return dish;
    }
 
    public void setDish(Dish dish) {
        this.dish = dish;
    }
 
    public int getQuantity() {
        return quantity;
    }
 
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    @Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderDate=" + orderDate + ", user=" + user + ", restaurant="
				+ restaurant + ", dish=" + dish + ", quantity=" + quantity + ", totalPrice=" + totalPrice
				+ ", trackingId=" + trackingId + "]";
	}
}