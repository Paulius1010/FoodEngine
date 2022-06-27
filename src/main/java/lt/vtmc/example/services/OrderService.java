package lt.vtmc.example.services;

import lt.vtmc.example.models.Dish;
import lt.vtmc.example.models.Order;
import lt.vtmc.example.models.User;
import lt.vtmc.example.payloads.responses.OrderResponse;
import lt.vtmc.example.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    public List<OrderResponse> getAllUserOrders() {
        String currentPrincipalEmail = getCurrentPrincipalEmail();
        User user = userService.getUserByEmail(currentPrincipalEmail).orElse(null);
        if(user != null){
            List<Order> orders = orderRepository.findByUser(user);
            Set<Dish> dishesSet = orders.stream().map(order -> order.getDish()).collect(Collectors.toSet());
            List<OrderResponse> orderResponses = new ArrayList<>();
            for (Dish dish : dishesSet) {
                Long countOrders = orders.stream().filter(order -> order.getDish().equals(dish)).count();
                orderResponses.add(new OrderResponse(
                        user.getId(),
                        user.getEmail(),
                        dish.getMenu().getName(),
                        dish.getMenu().getRestaurant().getName(),
                        dish.getId(),
                        dish.getName(),
                        dish.getPrice().toString(),
                        countOrders));
            }
            return orderResponses.stream().sorted().collect(Collectors.toList());
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

    public String deleteOrder(Long dishId) {
        String currentPrincipalEmail = getCurrentPrincipalEmail();
        User user = userService.getUserByEmail(currentPrincipalEmail).orElse(null);
        if (getAllUserOrders().stream().anyMatch(order -> order.getDishId().equals(dishId))) {
            Order deletingOrder = orderRepository.findByUser(user).stream().filter(order -> order.getDish().getId().equals(dishId)).findFirst().get();
            orderRepository.delete(deletingOrder);
            return "deleted";
        } else {
            return "not find";
        }
    }

    private String getCurrentPrincipalEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    public List<OrderResponse> getAllOrders() {
        List<User> users = userService.getAllUsers();
        List<OrderResponse> orderResponses = new ArrayList<>();
        if(!users.isEmpty()){
            for (User user : users) {
                List<Order> orders = orderRepository.findByUser(user);
                Set<Dish> dishesSet = orders.stream().map(order -> order.getDish()).collect(Collectors.toSet());
                for (Dish dish : dishesSet) {
                    Long countOrders = orders.stream().filter(order -> order.getDish().equals(dish)).count();
                    orderResponses.add(new OrderResponse(
                            user.getId(),
                            user.getEmail(),
                            dish.getMenu().getName(),
                            dish.getMenu().getRestaurant().getName(),
                            dish.getId(),
                            dish.getName(),
                            dish.getPrice().toString(),
                            countOrders));
                }
            }
            return orderResponses.stream().sorted().collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }
}
