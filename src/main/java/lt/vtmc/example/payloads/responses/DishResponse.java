package lt.vtmc.example.payloads.responses;

public class DishResponse {
    private Long id;
    private Long restaurantId;
    private String restaurantName;
    private Long menuId;
    private String menuName;
    private String name;
    private String description;
    private String price;
    private Integer preparationTimeInMinutes;

    public DishResponse(Long id, Long restaurantId, String restaurantName, Long menuId, String menuName, String name, String description, String price, Integer preparationTimeInMinutes) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.menuId = menuId;
        this.menuName = menuName;
        this.name = name;
        this.description = description;
        this.price = price;
        this.preparationTimeInMinutes = preparationTimeInMinutes;
    }

    public DishResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
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

    public void setPreparationTimeInMinutes(Integer preparationTimeInMinutes) {
        this.preparationTimeInMinutes = preparationTimeInMinutes;
    }

    @Override
    public String toString() {
        return "DishResponse{" +
                "id=" + id +
                ", restaurantId=" + restaurantId +
                ", restaurantName='" + restaurantName + '\'' +
                ", menuId=" + menuId +
                ", menuName='" + menuName + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                ", preparationTimeInMinutes=" + preparationTimeInMinutes +
                '}';
    }
}
