package lt.vtmc.example.controllers;

import lt.vtmc.example.models.Restaurant;
import lt.vtmc.example.payloads.requests.RestaurantRequest;
import lt.vtmc.example.payloads.responses.DishResponse;
import lt.vtmc.example.services.DishService;
import lt.vtmc.example.services.MenuService;
import lt.vtmc.example.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping(value = "/{restaurantId}/menu/dishes")
    public ResponseEntity<List<DishResponse>> fetchOneRestaurantAllDishes(@PathVariable Long restaurantId) {
        return ResponseEntity.ok().body(this.restaurantService.getOneRestaurantAllDishes(restaurantId));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Restaurant> saveNewRestaurant(@RequestBody RestaurantRequest restaurantRequest) {
        return ResponseEntity.ok().body(this.restaurantService.saveNewRestaurant(restaurantRequest));
    }

    @PutMapping(value = "/{restaurantId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable Long restaurantId, @RequestBody RestaurantRequest restaurantRequest) {
        return ResponseEntity.ok().body(this.restaurantService.updateRestaurant(restaurantId, restaurantRequest));
    }

    @DeleteMapping(value = "/{restaurantId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> deleteOneRestaurant(@PathVariable Long restaurantId) {
        return ResponseEntity.ok().body(this.restaurantService.deleteOneRestaurant(restaurantId));
    }
}
