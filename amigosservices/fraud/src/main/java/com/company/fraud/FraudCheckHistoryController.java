package com.company.fraud;

import com.company.clients.fraud.FraudCheckHistoryResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("api/v1/fraud-check")
public class FraudCheckHistoryController {

    private final FraudCheckHistoryService fraudCheckHistoryService;

    @GetMapping(path = "{customerId}")
    public FraudCheckHistoryResponse isFraudster(@PathVariable("customerId") Integer customerID) {
        boolean isFraudulentCustomer = fraudCheckHistoryService.isFraudulentCustomer(customerID);
        log.info("fraud check request for customer {}", customerID);

        return new FraudCheckHistoryResponse(isFraudulentCustomer);
    }
}
