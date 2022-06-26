package lt.vtmc.example.controllers;

import lt.vtmc.example.models.Dish;
import lt.vtmc.example.models.Menu;
import lt.vtmc.example.payloads.requests.DishRequest;
import lt.vtmc.example.payloads.requests.DishUpdateRequest;
import lt.vtmc.example.payloads.requests.MenuRequest;
import lt.vtmc.example.payloads.requests.MenuUpdateRequest;
import lt.vtmc.example.payloads.responses.DishResponse;
import lt.vtmc.example.payloads.responses.MenuResponse;
import lt.vtmc.example.services.DishService;
import lt.vtmc.example.services.MenuService;
import lt.vtmc.example.services.RestaurantService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin
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
    public ResponseEntity<List<MenuResponse>> fetchAllMenus() {
        return ResponseEntity.ok().body(this.menuService.getAllMenus());
    }

    @GetMapping(value = "/{restaurantId}/menu")
    public ResponseEntity<Set<Menu>> fetchAllRestaurantMenus(@PathVariable Long restaurantId) {
        return ResponseEntity.ok().body(this.restaurantService.getAllRestaurantMenus(restaurantId));
    }

    @PostMapping(value = "/menu")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Menu> saveNewMenu(@RequestBody MenuRequest menuRequest) {
        return ResponseEntity.ok().body(this.menuService.saveMenu(menuRequest));
    }

    @PutMapping(value = "/menu")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Menu> updateMenu(@RequestBody MenuUpdateRequest menuUpdateRequest) {
        return ResponseEntity.ok().body(this.menuService.updateMenu(menuUpdateRequest));
    }

    @DeleteMapping(value = "/menu/{menuId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> deleteMenu(@PathVariable Long menuId) {
        return ResponseEntity.ok().body(this.menuService.deleteMenu(menuId));
    }

    @GetMapping(value = "/menu/dishes")
    public ResponseEntity<List<DishResponse>> fetchAllDishes() {
        return ResponseEntity.ok().body(this.dishService.getAllDishes());
    }

    @GetMapping(value = "/menu/{menuId}/dishes")
    public ResponseEntity<Set<DishResponse>> fetchAllRestaurantMenuDishes(@PathVariable Long menuId) {
        return ResponseEntity.ok().body(this.menuService.getAllRestaurantMenuDishes(menuId));
    }

    @PostMapping(value = "/menu/dishes")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Dish> saveNewDish(@RequestBody DishRequest dishRequest) {
        return ResponseEntity.ok().body(this.dishService.saveDish(dishRequest));
    }

    @PutMapping(value = "/menu/dishes")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Dish> updateDish(@RequestBody DishUpdateRequest dishUpdateRequest) {
        return ResponseEntity.ok().body(this.dishService.updateDish(dishUpdateRequest));
    }

    @DeleteMapping(value = "/menu/dishes/{dishId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> deleteDish(@PathVariable Long dishId) {
        return ResponseEntity.ok().body(this.dishService.deleteDish(dishId));
    }

}
