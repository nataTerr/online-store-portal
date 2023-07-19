package nata.project.openFeign.controller;

import nata.project.openFeign.clients.OrderApiFeignClient;
import nata.project.openFeign.dtos.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderApiController {

    @Autowired
    private OrderApiFeignClient feignClient;

    @GetMapping("/{orderId}/detail/info")
    public OrderDto getOrder(@PathVariable Long orderId) {
        return feignClient.getOrder(orderId);
    }

    @GetMapping("/users/{clientId}")
    public List<OrderDto> getOrdersByClientId(@PathVariable Long clientId) {
        return feignClient.getOrdersByClientId(clientId);
    }
}

