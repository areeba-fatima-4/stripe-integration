package stripe.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class ProductsBoughtDto {
    private UUID id;
    private UUID userId;
    private UUID productId;
    private Long quantity;
}
