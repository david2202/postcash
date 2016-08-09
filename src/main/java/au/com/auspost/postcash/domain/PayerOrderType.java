package au.com.auspost.postcash.domain;

/**
 * Created by david on 9/08/16.
 */
public enum PayerOrderType {
    SINGLE("S"),
    BULK("B");

    private String code;

    private PayerOrderType(String code) {
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
