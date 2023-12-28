package com.company.customer;

import com.company.clients.fraud.FraudCheckHistoryResponse;
import com.company.clients.fraud.FraudClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final FraudClient fraudClient;

    public void registerCustomer(CustomerRegistrationRequest customerRegistrationRequest) {
        Customer customer = Customer.builder()
                .firstName(customerRegistrationRequest.firstName())
                .lastName(customerRegistrationRequest.lastName())
                .email(customerRegistrationRequest.email())
                .build();
        // todo: check if email valid
        // todo: check if email not taken
        customerRepository.saveAndFlush(customer);

        FraudCheckHistoryResponse fraudCheckHistoryResponse = fraudClient.isFraudster(customer.getId());

        if (fraudCheckHistoryResponse.isFraudster()) {
            throw new IllegalStateException("fraudster");
        }
        // todo: send notification
    }
}
