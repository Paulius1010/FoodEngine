package lt.vtmc.example.controllers;

import lt.vtmc.example.models.Dish;
import lt.vtmc.example.models.Menu;
import lt.vtmc.example.models.Restaurant;
import lt.vtmc.example.payloads.requests.DishRequest;
import lt.vtmc.example.payloads.requests.MenuRequest;
import lt.vtmc.example.payloads.requests.RestaurantRequest;
import lt.vtmc.example.services.DishService;
import lt.vtmc.example.services.MenuService;
import lt.vtmc.example.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/restaurants")
public class RestaurantController {

    private  final RestaurantService restaurantService;
    private final MenuService menuService;
    private final DishService dishService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService, MenuService menuService, DishService dishService) {
        this.restaurantService = restaurantService;
        this.menuService = menuService;
        this.dishService = dishService;
    }

    @GetMapping
    public ResponseEntity<List<Restaurant>> fetchAllRestaurants() {
        return ResponseEntity.ok().body(this.restaurantService.getAllRestaurants());
    }

    @GetMapping(value = "/{restaurantId}")
    public ResponseEntity<Restaurant> fetchOneRestaurant(@PathVariable Long restaurantId) {
        return ResponseEntity.ok().body(this.restaurantService.getOneRestaurant(restaurantId));
    }

    @PostMapping
    public ResponseEntity<Restaurant> saveNewRestaurant(@RequestBody RestaurantRequest restaurantRequest) {
        return ResponseEntity.ok().body(this.restaurantService.saveNewRestaurant(restaurantRequest));
    }


    @PutMapping(value = "/{restaurantId}")
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable Long restaurantId, @RequestBody RestaurantRequest restaurantRequest) {
        return ResponseEntity.ok().body(this.restaurantService.updateRestaurant(restaurantId, restaurantRequest));
    }

    @DeleteMapping(value = "/{restaurantId}")
    public ResponseEntity<String> deleteOneRestaurant(@PathVariable Long restaurantId) {
        return ResponseEntity.ok().body(this.restaurantService.deleteOneRestaurant(restaurantId));
    }


}
