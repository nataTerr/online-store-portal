package nata.project.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ItemNotFoundException extends RuntimeException {
    private static final Logger logger = LoggerFactory.getLogger(ItemNotFoundException.class);
    public ItemNotFoundException(String message) {
        super(message);
        logger.error(message);
    }
}
