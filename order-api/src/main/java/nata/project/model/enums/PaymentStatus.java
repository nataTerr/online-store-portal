package nata.project.model.enums;

public enum PaymentStatus {
    PAID("оплачен"),
    NOTPAID("не оплачен");

    private final String text;

    PaymentStatus(String text) {
        this.text = text;
    }

    public String getUrl() {
        return text;
    }
}
