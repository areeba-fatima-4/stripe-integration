package stripe.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StripeSessionDto {
    private String url;
    private String sessionId;

}
