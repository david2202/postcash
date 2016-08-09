package au.com.auspost.postcash.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MoneyOrderOrder {
    private String transactionId;
    private MoneyOrderType type;
    private Date transactionDateTime;
    private String customerNumber;
    private String orderNumber;
    private List<MoneyOrder> moneyOrders = new ArrayList<>();

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public MoneyOrderType getType() {
        return type;
    }

    public void setType(MoneyOrderType type) {
        this.type = type;
    }

    public Date getTransactionDateTime() {
        return transactionDateTime;
    }

    public void setTransactionDateTime(Date transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public List<MoneyOrder> getMoneyOrders() {
        return moneyOrders;
    }

    public void setMoneyOrders(List<MoneyOrder> moneyOrders) {
        this.moneyOrders = moneyOrders;
    }
}
