package br.com.event.management.system.application.events.customers;

import br.com.event.management.system.core.events.application.dto.RegisterCustomerInput;
import br.com.event.management.system.core.events.application.service.CustomerService;
import br.com.event.management.system.core.events.domain.entities.Customer;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/customers")
public class CustomersController {

  private final CustomerService customerService;

  @GetMapping
  public ResponseEntity<List<Customer>> list() {
    final var response = this.customerService.findAll();
    return ResponseEntity.ok(response);
  }

  @PostMapping
  public ResponseEntity<Customer> create(@RequestBody final RegisterCustomerInput input) {
    final var response = this.customerService.register(input);
    return ResponseEntity.ok(response);
  }

}
