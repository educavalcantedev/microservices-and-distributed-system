package com.company.customer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final RestTemplate restTemplate;

    public void registerCustomer(CustomerRegistrationRequest customerRegistrationRequest) {
        Customer customer = Customer.builder()
                .firstName(customerRegistrationRequest.firstName())
                .lastName(customerRegistrationRequest.lastName())
                .email(customerRegistrationRequest.email())
                .build();
        // todo: check if email valid
        // todo: check if email not taken
        customerRepository.saveAndFlush(customer);
        // todo: check if fraudster
        FraudCheckHistoryResponse fraudCheckHistoryResponse = restTemplate.getForObject(
                "http://fraud/api/v1/fraud-check/{customerId}",
                FraudCheckHistoryResponse.class,
                customer.getId()
        );

        if (fraudCheckHistoryResponse.isFraudster()) {
            throw new IllegalStateException("fraudster");
        }
        // todo: send notification
    }
}
