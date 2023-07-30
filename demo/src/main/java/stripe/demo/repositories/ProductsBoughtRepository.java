package stripe.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import stripe.demo.domain.models.ProductsBought;

import java.util.UUID;

public interface ProductsBoughtRepository extends JpaRepository<ProductsBought, UUID> {
    ProductsBought findByUserIdAndProductId(UUID userId, UUID productId);
}
