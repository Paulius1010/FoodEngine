package lt.vtmc.example.services;

import lt.vtmc.example.models.Dish;
import lt.vtmc.example.models.Menu;
import lt.vtmc.example.models.Restaurant;
import lt.vtmc.example.payloads.requests.MenuRequest;
import lt.vtmc.example.repositories.MenuRepository;
import lt.vtmc.example.repositories.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MenuService {
    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;

    public MenuService(MenuRepository menuRepository, RestaurantRepository restaurantRepository) {
        this.menuRepository = menuRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public Menu saveMenu(Long restaurantId, MenuRequest menuRequest) {
        Restaurant restaurant = this.restaurantRepository.findById(restaurantId).get();
                Menu menu = new Menu(
                        restaurant,
               menuRequest.getName()
        );
        this.menuRepository.save(menu);
        return menu;
    }

    public List<Menu> getAllMenus() {
        return this.menuRepository.findAll();
    }

    public Set<Dish> getAllRestaurantMenuDishes(Long menuId) {
        Optional<Menu> menu = this.menuRepository.findById(menuId);
        if (menu.isPresent()) {
            return menu.get().getDishes();
        } else {
            return null;
        }
    }

    public Menu getMenu(Long menuId) {
        return this.menuRepository.findById(menuId).orElse(null);
    }

    public Menu updateMenu(Long menuId, MenuRequest menuRequest) {
        Optional<Menu> menu = this.menuRepository.findById(menuId);
        if (menu.isPresent()) {
            menu.get().setName(menuRequest.getName());
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
