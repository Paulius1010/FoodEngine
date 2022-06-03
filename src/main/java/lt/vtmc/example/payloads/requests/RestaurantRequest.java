package lt.vtmc.example.payloads.requests;

public class RestaurantRequest {

    private String name;
    private String entityCode;
    private String city;
    private String address;

    public RestaurantRequest(String name, String entityCode, String city, String address) {
        this.name = name;
        this.entityCode = entityCode;
        this.city = city;
        this.address = address;
    }

    public RestaurantRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEntityCode() {
        return entityCode;
    }

    public void setEntityCode(String entityCode) {
        this.entityCode = entityCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "RestaurantRequest{" +
                ", name='" + name + '\'' +
                ", entityCode='" + entityCode + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
