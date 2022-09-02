package api.micro.store.shopping.repo;

import api.micro.store.shopping.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface InvoiceRepo extends JpaRepository<Invoice, Long> {

    List<Invoice> findByCustomerId(Long customerId);
    Invoice findByNumberInvoice(String numberInvoice);

}
