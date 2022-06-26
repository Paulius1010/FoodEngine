package lt.vtmc.example.payloads.requests;

public class MenuUpdateRequest {


    private Long menuId;
    private Long restaurantId;
    private String name;

    public MenuUpdateRequest(Long menuId, Long restaurantId, String name) {
        this.menuId = menuId;
        this.restaurantId = restaurantId;
        this.name = name;
    }

    public MenuUpdateRequest() {
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
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
