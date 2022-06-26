package lt.vtmc.example.services;

import lt.vtmc.example.models.Dish;
import lt.vtmc.example.models.Menu;
import lt.vtmc.example.payloads.requests.DishRequest;
import lt.vtmc.example.payloads.requests.DishUpdateRequest;
import lt.vtmc.example.payloads.responses.DishResponse;
import lt.vtmc.example.repositories.DishRepository;
import lt.vtmc.example.repositories.MenuRepository;
import lt.vtmc.example.repositories.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DishService {
    private final DishRepository dishRepository;
    private final MenuRepository menuRepository;

    public DishService(DishRepository dishRepository, MenuRepository menuRepository) {
        this.dishRepository = dishRepository;
        this.menuRepository = menuRepository;
    }

    public Dish saveDish(DishRequest dishRequest) {
        BigDecimal price = new BigDecimal(dishRequest.getPrice());
        Menu menu = this.menuRepository.findById(dishRequest.getMenuId()).get();
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

    public List<DishResponse> getAllDishes() {
        List<Dish> dishes = this.dishRepository.findAll();
        if (!dishes.isEmpty()) {
            return dishes.stream().map(dish -> new DishResponse(
                    dish.getId(),
                    dish.getMenu().getRestaurant().getId(),
                    dish.getMenu().getRestaurant().getName(),
                    dish.getMenu().getId(),
                    dish.getMenu().getName(),
                    dish.getName(),
                    dish.getDescription(),
                    dish.getPrice().toString(),
                    dish.getPreparationTimeInMinutes())).collect(Collectors.toList());
        } else {
            return null;
        }
    }

    public Dish updateDish(DishUpdateRequest dishUpdateRequest) {
        Dish dish = this.dishRepository.findById(dishUpdateRequest.getDishId()).get();
        dish.setName(dishUpdateRequest.getName());
        dish.setDescription(dishUpdateRequest.getDescription());
        dish.setPrice(new BigDecimal(dishUpdateRequest.getPrice()));
        dish.setPreparationTimeInMinutes(dishUpdateRequest.getPreparationTimeInMinutes());
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
