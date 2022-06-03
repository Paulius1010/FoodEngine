package lt.vtmc.example.payloads.requests;

import lt.vtmc.example.models.Restaurant;

public class MenuRequest {

    private Long restaurantId;

    private String name;

    public MenuRequest(Long restaurantId, String name) {
        this.restaurantId = restaurantId;
        this.name = name;
    }

    public MenuRequest() {
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MenuRequest{" +
                "restaurantId=" + restaurantId +
                ", name='" + name + '\'' +
                '}';
    }
}
