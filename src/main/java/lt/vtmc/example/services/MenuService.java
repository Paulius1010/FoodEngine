package lt.vtmc.example.services;

import lt.vtmc.example.models.Dish;
import lt.vtmc.example.models.Menu;
import lt.vtmc.example.models.Restaurant;
import lt.vtmc.example.payloads.requests.MenuRequest;
import lt.vtmc.example.repositories.MenuRepository;
import lt.vtmc.example.repositories.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class MenuService {
    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;

    public MenuService(MenuRepository menuRepository, RestaurantRepository restaurantRepository) {
        this.menuRepository = menuRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public Menu saveMenu(Long id, MenuRequest menuRequest) {
        Long restaurantId = Long.valueOf(id);
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
        return this.menuRepository.findById(Long.valueOf(menuId)).get().getDishes();
    }

    public Menu updateMenu(Long menuId, MenuRequest menuRequest) {
        Menu menu = this.menuRepository.findById(menuId).get();
        menu.setName(menuRequest.getName());
        this.menuRepository.save(menu);
        return menu;
    }

    public String deleteMenu(Long menuId) {
        this.menuRepository.deleteById(menuId);
        return "deleted";
    }
}
