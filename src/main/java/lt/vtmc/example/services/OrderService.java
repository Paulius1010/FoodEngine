package lt.vtmc.example.services;

import lt.vtmc.example.models.Dish;
import lt.vtmc.example.models.Order;
import lt.vtmc.example.models.User;
import lt.vtmc.example.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private UserService userService;
    private DishService dishService;

    @Autowired
    public OrderService(OrderRepository orderRepository, UserService userService, DishService dishService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.dishService = dishService;
    }

    public List<Order> getAllUserOrders() {
        String currentPrincipalEmail = getCurrentPrincipalEmail();
        User user = userService.getUserByEmail(currentPrincipalEmail).orElse(null);
        if(user != null){
            return orderRepository.findByUser(user);
        } else {
            return Collections.emptyList();
        }
    }

    public Order saveNewOrder(Long id) {
        String currentPrincipalEmail = getCurrentPrincipalEmail();
        User user = userService.getUserByEmail(currentPrincipalEmail).orElse(null);
        Dish dish = dishService.getDishById(id);
        Order order = new Order(user, dish);
        return orderRepository.save(order);
    }

    public String deleteOrder(Long id) {
        String currentPrincipalEmail = getCurrentPrincipalEmail();
        User user = userService.getUserByEmail(currentPrincipalEmail).orElse(null);
        if (getAllUserOrders().stream().anyMatch(order -> order.getId().equals(id))) {
            orderRepository.deleteById(id);
            return "deleted";
        } else {
            return "not find";
        }
    }

    private String getCurrentPrincipalEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
