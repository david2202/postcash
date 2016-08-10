package au.com.auspost.postcash.controller;

import au.com.auspost.postcash.domain.MoneyOrderOrder;
import au.com.auspost.postcash.domain.MoneyOrderRequest;
import au.com.auspost.postcash.service.MoneyOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class MoneyOrderRestController {
    @Autowired
    private MoneyOrderService moneyOrderService;

    @RequestMapping(path = "/rest/moneyOrder", method = RequestMethod.POST)
    public @ResponseBody MoneyOrderRequest create(@RequestBody MoneyOrderRequest request) {
        moneyOrderService.create(request);
        return request;
    }
}
