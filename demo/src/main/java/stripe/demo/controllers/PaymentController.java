package stripe.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import stripe.demo.dtos.StripeSessionDto;
import stripe.demo.services.PaymentService;

import java.util.UUID;

@RestController
@RequestMapping()
public class PaymentController {

    @Autowired private PaymentService paymentService;
    /**
     * Get stripe session url
     *
     */
    @PostMapping("/create-checkout-session")
    public ResponseEntity<StripeSessionDto> getStripeSession(
            @RequestParam("planId") UUID planId,
            @RequestParam(required = false) UUID userId,
            @RequestParam("quantity") Long quantity) {
        var result = paymentService.getStripeSessionResponse(planId, userId, quantity);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
