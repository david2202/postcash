package au.com.auspost.postcash.domain;

public enum DeliveryType {
    PAPER("P"),
    VOUCHER("V"),
    CUSTOMER_COLLECT("C");

    private String code;

    private DeliveryType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code;
    }
}
