package au.com.auspost.postcash.domain;

public enum MoneyOrderType {
    STANDARD("00"),
    EXPRESS("01");

    private String code;

    private MoneyOrderType(String code) {
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
