package au.com.auspost.postcash.domain;

import java.math.BigDecimal;

public class MoneyOrder {
    private static int nextNumber = 1000000;

    private BigDecimal amount;
    private PayeeType payeeType;
    private String payeeName;
    private String payeeEmail;
    private PayerOrderType orderType;
    private Integer lineNumber;
    private DeliveryType deliveryType;
    private Integer number;
    private Integer checkDigits;
    private String barcodeText;
    private String barcodeBase64;
    private String barcodeMimeType;

    public MoneyOrder() {
        number = nextNumber++;
    }

    public String calculateBarcodeText() {
        //return "*" + number + calculateCheckDigits();
        return "136YOUR REWARD VOUCHER LUCKY WIN " + String.format("%05d", amount.multiply(new BigDecimal(100)).intValue());
    }

    public Integer calculateCheckDigits() {
        return number % 97;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public PayeeType getPayeeType() {
        return payeeType;
    }

    public void setPayeeType(PayeeType payeeType) {
        this.payeeType = payeeType;
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    public String getPayeeEmail() {
        return payeeEmail;
    }

    public void setPayeeEmail(String payeeEmail) {
        this.payeeEmail = payeeEmail;
    }

    public PayerOrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(PayerOrderType orderType) {
        this.orderType = orderType;
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(DeliveryType deliveryType) {
        this.deliveryType = deliveryType;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getCheckDigits() {
        return checkDigits;
    }

    public void setCheckDigits(Integer checkDigits) {
        this.checkDigits = checkDigits;
    }

    public String getBarcodeText() {
        return barcodeText;
    }

    public void setBarcodeText(String barcodeText) {
        this.barcodeText = barcodeText;
    }

    public String getBarcodeBase64() {
        return barcodeBase64;
    }

    public void setBarcodeBase64(String barcodeBase64) {
        this.barcodeBase64 = barcodeBase64;
    }

    public String getBarcodeMimeType() {
        return barcodeMimeType;
    }

    public void setBarcodeMimeType(String barcodeMimeType) {
        this.barcodeMimeType = barcodeMimeType;
    }
}
