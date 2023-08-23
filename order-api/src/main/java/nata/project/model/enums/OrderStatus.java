package nata.project.model.enums;

public enum OrderStatus {
    FRAMED("оформлен"),
    DELIVERY("передан в доставку"),
    SERVICE("в службе доставки"),
    WAY("в пути"),
    EXPECTS("в пункте/у курьера"),
    RECEIVED("получен");

    private final String text;

    OrderStatus(String text) {
        this.text = text;
    }

    public String getUrl() {
        return text;
    }
}
