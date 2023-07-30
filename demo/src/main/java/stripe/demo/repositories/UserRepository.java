package stripe.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import stripe.demo.domain.models.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
