package lt.vtmc.example.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "menus")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    @JsonIgnore
    private Restaurant restaurant;

    private String name;

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "menus_dishes",
//            joinColumns = @JoinColumn(name = "menu_id"),
//            inverseJoinColumns = @JoinColumn(name = "dish_id"))
    @OneToMany(mappedBy = "menu", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<Dish> dishes;


    public Menu(Restaurant restaurant, String name) {
        this.restaurant = restaurant;
        this.name = name;
        this.dishes = new HashSet<>();
    }

    public Menu(Restaurant restaurant, String name, Set<Dish> dishes) {
        this.restaurant = restaurant;
        this.name = name;
        this.dishes = dishes;
    }

    public Menu() {
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
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

    public Set<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(Set<Dish> dishes) {
        this.dishes = dishes;
    }

    public Dish addDish(Dish dish) {
        this.dishes.add(dish);
        return  dish;
    }

    public Dish removeDish(Dish dish) {
        this.dishes.remove(dish);
        return  dish;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", restaurant=" + restaurant +
                ", name='" + name + '\'' +
                ", dishes=" + dishes +
                '}';
    }
}
