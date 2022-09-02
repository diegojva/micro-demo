package app.micro.store.customer.repo;

import app.micro.store.customer.entities.Customer;
import app.micro.store.customer.entities.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository  extends JpaRepository<Customer,Long> {
     Customer findByNumberID(String numberID);
     List<Customer> findByLastName(String lastName);
     List<Customer> findByRegion(Region region);
}