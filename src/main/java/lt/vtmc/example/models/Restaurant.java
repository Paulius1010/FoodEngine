package lt.vtmc.example.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "restaurants")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String entityCode;

    private String city;

    private String address;

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<Menu> menus;

    public Restaurant(String name, String entityCode, String city, String address) {
        this.name = name;
        this.entityCode = entityCode;
        this.city = city;
        this.address = address;
        this.menus = new HashSet<>();
    }

    public Restaurant(String name, String entityCode, String city, String address, Set<Menu> menus) {
        this.name = name;
        this.entityCode = entityCode;
        this.city = city;
        this.address = address;
        this.menus = menus;
    }

    public Restaurant() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<Menu> getMenus() {
        return menus;
    }

    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
    }

    public Menu addMenu(Menu menu) {
        this.menus.add(menu);
        return menu;
    }

    public Menu removeMenu(Menu menu) {
        this.menus.remove(menu);
        return menu;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", entityCode='" + entityCode + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", menus=" + menus +
                '}';
    }
}
