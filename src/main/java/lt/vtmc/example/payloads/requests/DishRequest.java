package lt.vtmc.example.payloads.requests;

public class DishRequest {

    private String name;

    private String description;

    private String price;

    private Integer preparationTimeInMinutes;

    public DishRequest() {
    }

    public DishRequest(String name, String description, String price, Integer preparationTimeInMinutes) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.preparationTimeInMinutes = preparationTimeInMinutes;
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
