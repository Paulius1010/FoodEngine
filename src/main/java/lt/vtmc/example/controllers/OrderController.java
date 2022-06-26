package lt.vtmc.example.controllers;

import lt.vtmc.example.models.Order;
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

    @GetMapping
    public ResponseEntity<List<Order>> fetchAllUserOrders() {
        return ResponseEntity.ok().body(this.orderService.getAllUserOrders());
    }

    @PostMapping(value = "/{dishId}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Order> saveNewUserOrder(@PathVariable Long dishId) {
        return ResponseEntity.ok().body(this.orderService.saveNewOrder(dishId));
    }

    @DeleteMapping(value = "/{orderId}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<String> deleteOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok().body(this.orderService.deleteOrder(orderId));
    }
}
