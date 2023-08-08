package nata.project.model.dto.response.orders;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order {

    private Long id;
    private Long productId;
    private int count;
    private Long userId;

    public Order() {
    }

    public Order(Long id, Long productId, int count, Long userId) {
        this.id = id;
        this.productId = productId;
        this.count = count;
        this.userId = userId;
    }
}
