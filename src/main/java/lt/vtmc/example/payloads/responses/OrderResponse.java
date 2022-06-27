package lt.vtmc.example.payloads.responses;

public class OrderResponse {

    private Long userId;
    private String userEmail;
    private String menuName;
    private String restaurantName;
    private Long dishId;
    private String dishName;
    private String dishPrice;
    private Long orders;

    public OrderResponse(Long userId, String userEmail, String menuName, String restaurantName, Long dishId, String dishName, String dishPrice, Long orders) {
        this.userId = userId;
        this.userEmail = userEmail;
        this.menuName = menuName;
        this.restaurantName = restaurantName;
        this.dishId = dishId;
        this.dishName = dishName;
        this.dishPrice = dishPrice;
        this.orders = orders;
    }

    public OrderResponse() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public Long getDishId() {
        return dishId;
    }

    public void setDishId(Long dishId) {
        this.dishId = dishId;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getDishPrice() {
        return dishPrice;
    }

    public void setDishPrice(String dishPrice) {
        this.dishPrice = dishPrice;
    }

    public Long getOrders() {
        return orders;
    }

    public void setOrders(Long orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "OrderResponse{" +
                "userId=" + userId +
                ", userEmail='" + userEmail + '\'' +
                ", menuName='" + menuName + '\'' +
                ", restaurantName='" + restaurantName + '\'' +
                ", dishId=" + dishId +
                ", dishName='" + dishName + '\'' +
                ", orders=" + orders +
                '}';
    }
}
