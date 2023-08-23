package nata.project.model.enums;

public enum Delivery {
    MAIL("почтой"),
    POINT("пункт выдачи заказа"),
    COURIER("курьером");

    private final String text;

    Delivery(String text) {
        this.text = text;
    }

    public String getUrl() {
        return text;
    }
}
