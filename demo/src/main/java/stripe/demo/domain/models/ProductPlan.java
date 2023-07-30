package stripe.demo.domain.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "product_plans")
public class ProductPlan {

    @Id
    private UUID id;

    @Column(name = "price_id")
    private String priceId;

    @Column(name = "product_id")
    private String productID;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "active")
    private Boolean active;

}
