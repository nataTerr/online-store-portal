package nata.project.clients.openFeign.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto {

    private Long id;
    private Long productId;
    private int count;
    private Long userId;

}
