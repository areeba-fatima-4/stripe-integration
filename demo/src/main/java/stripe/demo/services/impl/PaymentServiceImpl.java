package stripe.demo.services.impl;

import com.stripe.model.Event;
import com.stripe.model.EventDataObjectDeserializer;
import com.stripe.model.PaymentIntent;
import com.stripe.model.StripeObject;
import com.stripe.net.ApiResource;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import stripe.demo.domain.models.User;
import stripe.demo.dtos.StripeSessionDto;
import stripe.demo.payment.gateways.StripeClient;
import stripe.demo.repositories.ProductPlanRepository;
import stripe.demo.repositories.ProductsBoughtRepository;
import stripe.demo.repositories.UserRepository;
import stripe.demo.services.PaymentService;

import javax.transaction.Transactional;
import java.util.UUID;

@Slf4j
@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    private static final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);


    @Value("${stripe.billing.secretKey}")
    private String stripeBillingSecretKey;

    @Autowired ProductPlanRepository productPlanRepository;
    @Autowired ProductsBoughtRepository productsBoughtRepository;
    @Autowired UserRepository userRepository;
    @Autowired StripeClient stripeClient;

    @Override
    public StripeSessionDto getStripeSessionResponse(UUID planId, UUID userId, Long quantity) {
        var productPlan = productPlanRepository.findById(planId).orElse(null);
        User user = null;
        if (userId != null)
                user = userRepository.findById(userId).orElse(null);
        com.stripe.model.checkout.Session session;
        if (user == null) {
            session = stripeClient.createGuestSession(productPlan.getPriceId(), quantity);
        } else {
            session = stripeClient.createSession(productPlan.getPriceId(), user.getCustomerId(), quantity);
        }
        return new StripeSessionDto(session.getUrl(), session.getId());

    }

}
