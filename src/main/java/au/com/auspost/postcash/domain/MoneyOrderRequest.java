package au.com.auspost.postcash.domain;

import java.util.ArrayList;
import java.util.List;

public class MoneyOrderRequest {
    private List<MoneyOrderOrder> orders  = new ArrayList<>();

    public List<MoneyOrderOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<MoneyOrderOrder> orders) {
        this.orders = orders;
    }
}
