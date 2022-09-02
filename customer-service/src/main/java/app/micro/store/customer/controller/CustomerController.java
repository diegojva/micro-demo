package app.micro.store.customer.controller;

import app.micro.store.customer.entities.Customer;
import app.micro.store.customer.entities.Region;

import app.micro.store.customer.service.CustomerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> listCustomer(@RequestParam(name = "categoryId", required = false) Long categoryId) {
        List<Customer> customers = new ArrayList<>();
        if (null == categoryId) {
            customers = customerService.findCustomerAll();
            if (customers.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
        } else {
            customers = customerService.findCustomersByRegion(Region.builder().id(categoryId).build());
            if (customers.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
        }


        return ResponseEntity.ok(customers);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") Long id) {
        Customer customer = customerService.getCustomer(id);
        if (null == customer) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customer);
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer, BindingResult result) {
        if (result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        Customer customerCreate = customerService.createCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerCreate);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") Long id, @RequestBody Customer customer) {
        customer.setId(id);
        Customer customerDB = customerService.updateCustomer(customer);
        if (customerDB == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customerDB);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") Long id) {
        Customer customerDelete = customerService.getCustomer(id);
        if (customerDelete == null) {
            return ResponseEntity.notFound().build();
        }
        customerDelete = customerService.deleteCustomer(customerDelete);
        return ResponseEntity.ok(customerDelete);
    }


    private String formatMessage(BindingResult result) {
        List<Map<String,String>> errors = result.getFieldErrors().stream()
                .map(err ->{
                    Map<String,String>  error =  new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;

                }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors).build();

        return stringToJSONString(errorMessage);
    }

    private String stringToJSONString(ErrorMessage errorMessage) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString="";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}