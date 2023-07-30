package stripe.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import stripe.demo.domain.models.ProductPlan;

import java.util.UUID;

public interface ProductPlanRepository extends JpaRepository <ProductPlan, UUID> {
}
