package nata.project.clients.openFeign.clients;

import nata.project.clients.openFeign.dtos.OrderDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(
        name = "order-api",
        url = "${order.api.url}"
)
public interface OrderApiFeignClient {

    @GetMapping("/orders/{orderId}/detail/info")
    OrderDto getOrder(@PathVariable Long orderId);

    @GetMapping("/orders/users/{clientId}")
    List<OrderDto> getOrdersByClientId(@PathVariable Long clientId);
}
