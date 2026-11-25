package net.yaya.customer_service.web;

import lombok.AllArgsConstructor;
import net.yaya.customer_service.entities.Customer;
import net.yaya.customer_service.repository.CustomerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@AllArgsConstructor
public class CustomerController {
    private CustomerRepository customerRepository;

    @GetMapping
    public List<Customer> all() {
        return customerRepository.findAll();
    }

    @GetMapping("/{id}")
    public Customer byId(@PathVariable Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    @PostMapping
    public Customer save(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }
}