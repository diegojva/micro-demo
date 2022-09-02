package api.micro.store.shopping.client;

import app.micro.store.product.entity.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductHystrixFallbackFactory implements ProductClient {
    @Override
    public ResponseEntity<Product> getProduct(Long id) {
        Product product = new Product();
        /*.firstName("none")
                .lastName("none")
                .email("none")
                .photoUrl("none").build();*/
        return ResponseEntity.ok(product);
    }

    @Override
    public ResponseEntity<Product> updateStockProduct(Long id, Double quantity) {
        return null;
    }

}
