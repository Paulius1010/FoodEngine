package lt.vtmc.example.payloads.requests;

public class DishRequest {


    private Long restaurantId;

    private Long menuId;

    private String name;

    private String description;

    private String price;

    private Integer preparationTimeInMinutes;

    public DishRequest() {
    }

    public DishRequest(Long restaurantId, Long menuId, String name, String description, String price, Integer preparationTimeInMinutes) {
        this.restaurantId = restaurantId;
        this.menuId = menuId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.preparationTimeInMinutes = preparationTimeInMinutes;
    }



    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getPreparationTimeInMinutes() {
        return preparationTimeInMinutes;
    }

    public void setPreparationTimeInMinutes(Integer preparationTime) {
        this.preparationTimeInMinutes = preparationTime;
    }

    @Override
    public String toString() {
        return "DishRequest{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                ", preparationTimeInMinutes=" + preparationTimeInMinutes +
                '}';
    }
}
