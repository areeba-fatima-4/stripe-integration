package stripe.demo.services;

import stripe.demo.dtos.StripeSessionDto;

import java.util.UUID;

public interface PaymentService {
    StripeSessionDto getStripeSessionResponse(UUID planId, UUID userId, Long quantity);

}
