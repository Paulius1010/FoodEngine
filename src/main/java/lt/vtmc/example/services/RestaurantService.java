package lt.vtmc.example.services;

import lt.vtmc.example.models.Dish;
import lt.vtmc.example.models.Menu;
import lt.vtmc.example.models.Restaurant;
import lt.vtmc.example.payloads.requests.RestaurantRequest;
import lt.vtmc.example.payloads.responses.DishResponse;
import lt.vtmc.example.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public List<Restaurant> getAllRestaurants() {
        return this.restaurantRepository.findAll();
    }

    public Restaurant saveNewRestaurant(RestaurantRequest restaurantRequest) {
        Restaurant restaurant = new Restaurant(
                restaurantRequest.getName(),
                restaurantRequest.getEntityCode(),
                restaurantRequest.getCity(),
                restaurantRequest.getAddress()
        );
        this.restaurantRepository.save(restaurant);
        return restaurant;
    }

    public Set<Menu> getAllRestaurantMenus(Long restaurantId) {
        return this.restaurantRepository.findById(Long.valueOf(restaurantId)).get().getMenus();

    }


    public Restaurant getOneRestaurant(Long restaurantId) {
        return this.restaurantRepository.findById(restaurantId).get();
    }

    public String deleteOneRestaurant(Long id) {
        this.restaurantRepository.deleteById(id);
        return "deleted";
    }

    public Restaurant updateRestaurant(Long id, RestaurantRequest restaurantRequest) {
        Restaurant restaurant = this.restaurantRepository.findById(id).get();
        restaurant.setName(restaurantRequest.getName());
        restaurant.setEntityCode(restaurantRequest.getEntityCode());
        restaurant.setCity(restaurantRequest.getCity());
        restaurant.setAddress(restaurantRequest.getAddress());
        this.restaurantRepository.save(restaurant);
        return restaurant;
    }

    public List<DishResponse> getOneRestaurantAllDishes(Long restaurantId) {
        Optional<Restaurant> restaurant = this.restaurantRepository.findById(restaurantId);
        if (restaurant.isPresent()) {
            Set<Menu> menus = restaurant.get().getMenus();
            if (!menus.isEmpty()) {
                return menus.stream().map(Menu::getDishes).flatMap(Set::stream).map(dish -> new DishResponse(
                        dish.getId(),
                        dish.getMenu().getRestaurant().getId(),
                        dish.getMenu().getRestaurant().getName(),
                        dish.getMenu().getId(),
                        dish.getMenu().getName(),
                        dish.getName(),
                        dish.getDescription(),
                        dish.getPrice().toString(),
                        dish.getPreparationTimeInMinutes()
                )).collect(Collectors.toList());
            }
        }
        return Collections.emptyList();
    }
}
