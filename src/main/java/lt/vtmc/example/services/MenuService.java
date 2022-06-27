package lt.vtmc.example.services;

import lt.vtmc.example.models.Menu;
import lt.vtmc.example.models.Restaurant;
import lt.vtmc.example.payloads.requests.MenuRequest;
import lt.vtmc.example.payloads.requests.MenuUpdateRequest;
import lt.vtmc.example.payloads.responses.DishResponse;
import lt.vtmc.example.payloads.responses.MenuResponse;
import lt.vtmc.example.repositories.MenuRepository;
import lt.vtmc.example.repositories.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MenuService {
    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;

    public MenuService(MenuRepository menuRepository, RestaurantRepository restaurantRepository) {
        this.menuRepository = menuRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public Menu saveMenu(MenuRequest menuRequest) {
        Restaurant restaurant = this.restaurantRepository.findById(menuRequest.getRestaurantId()).get();
                Menu menu = new Menu(
                        restaurant,
               menuRequest.getName()
        );
        this.menuRepository.save(menu);
        return menu;
    }

    public List<MenuResponse> getAllMenus() {
        List<Menu> menus = this.menuRepository.findAll();
        return menus.stream().map(menu -> new MenuResponse(
                menu.getId(),
                menu.getRestaurant().getId(),
                menu.getRestaurant().getName(),
                menu.getName())).toList();
    }

    public Set<DishResponse> getAllRestaurantMenuDishes(Long menuId) {
        Optional<Menu> menu = this.menuRepository.findById(menuId);
        if (menu.isPresent()) {
            return menu.get().getDishes().stream().map(dish -> new DishResponse(
                    dish.getId(),
                    dish.getMenu().getRestaurant().getId(),
                    dish.getMenu().getRestaurant().getName(),
                    dish.getMenu().getId(),
                    dish.getMenu().getName(),
                    dish.getName(),
                    dish.getDescription(),
                    dish.getPrice().toString(),
                    dish.getPreparationTimeInMinutes()
            )).collect(Collectors.toSet());
        } else {
            return null;
        }
    }

    public Menu getMenu(Long menuId) {
        return this.menuRepository.findById(menuId).orElse(null);
    }

    public Menu updateMenu(MenuUpdateRequest menuUpdateRequest) {
        Optional<Restaurant> restaurant = this.restaurantRepository.findById(menuUpdateRequest.getRestaurantId());
        Optional<Menu> menu = this.menuRepository.findById(menuUpdateRequest.getMenuId());
        if (menu.isPresent()) {
            menu.get().setName(menuUpdateRequest.getName());
            if (restaurant.isPresent()) {
                menu.get().setRestaurant(restaurant.get());
            }
            this.menuRepository.save(menu.get());
            return menu.get();
        } else {
            return null;
        }
    }

    public String deleteMenu(Long menuId) {
        this.menuRepository.deleteById(menuId);
        return "deleted";
    }
}
