package net_yaya.ebank_service.clients;

import net_yaya.ebank_service.model.Customer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerRestClientFallback implements CustomerRestClient {

    @Override
    public Customer findCustomerById(Long id) {
        return Customer.builder().id(id)
                .firstName("Not available").lastName("Not available").email("Not available")
                .build();
    }

    @Override
    public List<Customer> allCustomers() {
        return List.of();
    }
}