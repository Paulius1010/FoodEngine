package lt.vtmc.example.controllers;

import lt.vtmc.example.models.Dish;
import lt.vtmc.example.models.Menu;
import lt.vtmc.example.payloads.requests.DishRequest;
import lt.vtmc.example.payloads.requests.MenuRequest;
import lt.vtmc.example.services.DishService;
import lt.vtmc.example.services.MenuService;
import lt.vtmc.example.services.RestaurantService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/restaurants")
public class MenuController {
    private final MenuService menuService;
    private final RestaurantService restaurantService;
    private final DishService dishService;

    public MenuController(MenuService menuService, RestaurantService restaurantService, DishService dishService) {
        this.menuService = menuService;
        this.restaurantService = restaurantService;
        this.dishService = dishService;
    }

    @GetMapping(value = "/menu")
    public ResponseEntity<List<Menu>> fetchAllMenus() {
        return ResponseEntity.ok().body(this.menuService.getAllMenus());
    }

    @GetMapping(value = "/{restaurantId}/menu")
    public ResponseEntity<Set<Menu>> fetchAllRestaurantMenus(@PathVariable Long restaurantId) {
        return ResponseEntity.ok().body(this.restaurantService.getAllRestaurantMenus(restaurantId));
    }

    @PostMapping(value = "/{restaurantId}/menu")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Menu> saveNewMenu(@PathVariable Long restaurantId, @RequestBody MenuRequest menuRequest) {
        return ResponseEntity.ok().body(this.menuService.saveMenu(restaurantId, menuRequest));
    }

    @PutMapping(value = "/menu/{menuId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Menu> updateMenu(@PathVariable Long menuId, @RequestBody MenuRequest menuRequest) {
        return ResponseEntity.ok().body(this.menuService.updateMenu(menuId, menuRequest));
    }

    @DeleteMapping(value = "/menu/{menuId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> deleteMenu(@PathVariable Long menuId) {
        return ResponseEntity.ok().body(this.menuService.deleteMenu(menuId));
    }

    @GetMapping(value = "/menu/dishes")
    public ResponseEntity<List<Dish>> fetchAllDishes() {
        return ResponseEntity.ok().body(this.dishService.getAllDishes());
    }

    @GetMapping(value = "/menu/{menuId}/dishes")
    public ResponseEntity<Set<Dish>> fetchAllRestaurantMenuDishes(@PathVariable Long menuId) {
        return ResponseEntity.ok().body(this.menuService.getAllRestaurantMenuDishes(menuId));
    }

    @PostMapping(value = "/menu/{menuId}/dishes")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Dish> saveNewDish(@PathVariable Long menuId, @RequestBody DishRequest dishRequest) {
        return ResponseEntity.ok().body(this.dishService.saveDish(menuId, dishRequest));
    }

    @PutMapping(value = "/menu/dishes/{dishId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Dish> updateDish(@PathVariable Long dishId, @RequestBody DishRequest dishRequest) {
        return ResponseEntity.ok().body(this.dishService.updateDish(dishId, dishRequest));
    }

    @DeleteMapping(value = "/menu/dishes/{dishId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> deleteDish(@PathVariable Long dishId) {
        return ResponseEntity.ok().body(this.dishService.deleteDish(dishId));
    }

}
