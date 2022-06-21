package lt.vtmc.example.services;

import lt.vtmc.example.models.Dish;
import lt.vtmc.example.models.Menu;
import lt.vtmc.example.payloads.requests.DishRequest;
import lt.vtmc.example.repositories.DishRepository;
import lt.vtmc.example.repositories.MenuRepository;
import lt.vtmc.example.repositories.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DishService {
    private final DishRepository dishRepository;
    private final MenuRepository menuRepository;

    public DishService(DishRepository dishRepository, MenuRepository menuRepository) {
        this.dishRepository = dishRepository;
        this.menuRepository = menuRepository;
    }

    public Dish saveDish(Long id, DishRequest dishRequest) {
        Long menuId = Long.valueOf(id);
        BigDecimal price = new BigDecimal(dishRequest.getPrice());
        Menu menu = this.menuRepository.findById(menuId).get();
        Dish dish = new Dish(
                menu,
                dishRequest.getName(),
                dishRequest.getDescription(),
                price,
                dishRequest.getPreparationTimeInMinutes()
        );
        this.dishRepository.save(dish);
        return dish;
    }

    public List<Dish> getAllDishes() {
        return this.dishRepository.findAll();
    }

    public Dish updateDish(Long dishId, DishRequest dishRequest) {
        Dish dish = this.dishRepository.findById(dishId).get();
        dish.setName(dishRequest.getName());
        dish.setDescription(dishRequest.getDescription());
        dish.setPrice(new BigDecimal(dishRequest.getPrice()));
        dish.setPreparationTimeInMinutes(dishRequest.getPreparationTimeInMinutes());
        this.dishRepository.save(dish);
        return dish;
    }

    public String deleteDish(Long dishId) {
        this.dishRepository.deleteById(dishId);
        return "deleted";
    }

    public Dish getDishById(Long id) {
        return dishRepository.findById(id).orElse(null);
    }
}
