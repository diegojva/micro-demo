package app.micro.store.customer.service;

import app.micro.store.customer.entities.Customer;
import app.micro.store.customer.entities.Region;

import java.util.List;

public interface CustomerService {

     List<Customer> findCustomerAll();
     List<Customer> findCustomersByRegion(Region region);

     Customer createCustomer(Customer customer);
     Customer updateCustomer(Customer customer);
     Customer deleteCustomer(Customer customer);
     Customer getCustomer(Long id);



}