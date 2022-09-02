package api.micro.store.shopping.repo;

import api.micro.store.shopping.entities.InvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface InvoiceItemsRepo extends JpaRepository<InvoiceItem, Long> {


}
