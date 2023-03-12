package nata.project.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long productId) {
        super(String.format("Product with id %d not found", productId));
    }
}
