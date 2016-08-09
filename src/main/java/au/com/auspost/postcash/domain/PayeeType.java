package au.com.auspost.postcash.domain;

public enum PayeeType {
    INDIVIDUAL("1"),
    BUSINESS("2"),
    UNKNOWN("3");

    private String code;

    private PayeeType(String code) {
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
