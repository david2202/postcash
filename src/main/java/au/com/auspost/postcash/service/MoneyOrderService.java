package au.com.auspost.postcash.service;

import au.com.auspost.postcash.domain.MoneyOrder;
import au.com.auspost.postcash.domain.MoneyOrderOrder;
import au.com.auspost.postcash.domain.MoneyOrderRequest;
import org.springframework.stereotype.Component;

@Component
public class MoneyOrderService {

    public void create(MoneyOrderRequest request) {
        for (MoneyOrderOrder order : request.getOrders()) {
            processOrder(order);
        }
    }

    private void processOrder(MoneyOrderOrder order) {
        for (MoneyOrder moneyOrder: order.getMoneyOrders()) {
            processMoneyOrder(moneyOrder);
        }
    }

    private void processMoneyOrder(MoneyOrder moneyOrder) {
        moneyOrder.setNumber(12000005);
        moneyOrder.setCheckDigits(52);
        moneyOrder.setBarcodeText("*1501200000552");
        moneyOrder.setBarcodeBase64("A6674378B1");
    }
}
