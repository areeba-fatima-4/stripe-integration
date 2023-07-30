package stripe.demo.domain.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Data
@Table(name = "users")
@Entity
public class User {
    @Id
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "email_address")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "customer_id")
    private String  customerId;
}
