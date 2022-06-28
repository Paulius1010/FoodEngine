package lt.vtmc.example.controllers;

import lt.vtmc.example.models.Order;
import lt.vtmc.example.payloads.responses.OrderResponse;
import lt.vtmc.example.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/orders")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<OrderResponse>> fetchAllOrders() {
        return ResponseEntity.ok().body(this.orderService.getAllOrders());
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> fetchAllUserOrders() {
        return ResponseEntity.ok().body(this.orderService.getAllUserOrders());
    }

    @PostMapping(value = "/{dishId}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') ")
    public ResponseEntity<Order> saveNewUserOrder(@PathVariable Long dishId) {
        return ResponseEntity.ok().body(this.orderService.saveNewOrder(dishId));
    }

    @PostMapping(value = "/{userId}/{dishId}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') ")
    public ResponseEntity<Order> saveNewUserOrderByAdmin(@PathVariable Long userId, @PathVariable Long dishId) {
        return ResponseEntity.ok().body(this.orderService.saveNewOrderByAdmin(userId, dishId));
    }

    @DeleteMapping(value = "/{dishId}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') ")
    public ResponseEntity<String> deleteOrder(@PathVariable Long dishId) {
        return ResponseEntity.ok().body(this.orderService.deleteOrder(dishId));
    }

    @DeleteMapping(value = "/{userId}/{dishId}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') ")
    public ResponseEntity<String> deleteOrderByAdmin(@PathVariable Long userId, @PathVariable Long dishId) {
        return ResponseEntity.ok().body(this.orderService.deleteOrderByAdmin(userId, dishId));
    }
}
