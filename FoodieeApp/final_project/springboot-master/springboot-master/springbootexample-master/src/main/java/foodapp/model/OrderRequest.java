package foodapp.model;

public class OrderRequest {
    private int dishId;
    private int quantity;

    public OrderRequest() {
    }

    public OrderRequest(int dishId, int quantity) {
        this.dishId = dishId;
        this.quantity = quantity;
    }

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
