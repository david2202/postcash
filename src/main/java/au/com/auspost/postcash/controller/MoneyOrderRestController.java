package au.com.auspost.postcash.controller;

import au.com.auspost.postcash.domain.MoneyOrderOrder;
import au.com.auspost.postcash.domain.MoneyOrderRequest;
import au.com.auspost.postcash.service.MoneyOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MoneyOrderRestController {
    @Autowired
    private MoneyOrderService moneyOrderService;

    @RequestMapping(path = "/rest/moneyOrder", method = RequestMethod.POST)
    public @ResponseBody List<MoneyOrderOrder> create(@ModelAttribute(value = "request") MoneyOrderRequest request) {
        // return request.getOrders();
        MoneyOrderRequest r = new MoneyOrderRequest();
        MoneyOrderOrder o = new MoneyOrderOrder();
        r.getOrders().add(o);
        o.setTransactionId("12345");
        return r.getOrders();
    }
}
