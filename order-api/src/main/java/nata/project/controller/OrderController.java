package nata.project.controller;

import lombok.RequiredArgsConstructor;
import nata.project.model.dto.response.orders.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    @GetMapping("/{orderId}/detail/info")
    public Order getOrder(@PathVariable Long orderId) {
        return new Order(100L, 1L, 5, 20L);
    }

    @GetMapping("/users/{clientId}")
    public List<Order> getOrdersByClientId(@PathVariable Long clientId) {
        return Arrays.asList(new Order[]{
                new Order(100L, 1L, 5, 20L),
                new Order(200L, 2L, 3, 10L),
                new Order(200L, 1L, 2, 10L)});
    }
}
