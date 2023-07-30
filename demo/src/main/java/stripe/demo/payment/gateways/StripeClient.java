package stripe.demo.payment.gateways;

import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;
import com.stripe.model.Price;
import com.stripe.model.Product;
import com.stripe.model.checkout.Session;
import com.stripe.net.RequestOptions;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.PriceCreateParams;
import com.stripe.param.ProductCreateParams;
import com.stripe.param.checkout.SessionCreateParams;
import com.stripe.param.common.EmptyParam;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import stripe.demo.dtos.ProductPriceDto;
import stripe.demo.services.impl.PaymentServiceImpl;

@Slf4j
@Component
public class StripeClient {

    private static final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);

    @Value("${stripe.billing.secretKey}")
    private String stripeBillingSecretKey;

    public Session createSession() {
        try {
            SessionCreateParams params =
                    SessionCreateParams.builder()
                            .setMode(SessionCreateParams.Mode.PAYMENT)
                            .setSuccessUrl("http://localhost:8081/success.html")
                            .setCancelUrl("http://localhost:8081/canceled.html")
                            .addLineItem(
                                    SessionCreateParams.LineItem.builder()
                                            .setQuantity(1L)
                                            .setPriceData(
                                                    SessionCreateParams.LineItem.PriceData.builder()
                                                            .setCurrency("usd")
                                                            .setUnitAmount(2000L)
                                                            .setProductData(
                                                                    SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                            .setName("T-shirt")
                                                                            .build())
                                                            .build())
                                            .build())
                            .build();
            return Session.create(params, buildRequestOptions());

        } catch (StripeException se) {
            logger.error("Error creating session ", se);
            throw new RuntimeException("PAYMENT_GATEWAY_ERROR");
        }
    }

    public Session createGuestSession(String priceId, Long quantity) {
        try {
            SessionCreateParams params =
                    SessionCreateParams.builder()
                            .setMode(SessionCreateParams.Mode.PAYMENT)
                            .setSuccessUrl("http://localhost:8081/success.html")
                            .setCancelUrl("http://localhost:8081/canceled.html")
                            .addLineItem(
                                    SessionCreateParams.LineItem
                                            .builder()
                                            .setPrice(priceId)
                                            .setQuantity(quantity)
                                            .build()
                            )
                            .setCurrency("usd")
                            .build();
            return Session.create(params, buildRequestOptions());

        } catch (StripeException se) {
            logger.error("Error creating session ", se);
            throw new RuntimeException("PAYMENT_GATEWAY_ERROR");
        }
    }

    public Session createSession(String priceId, String customerId, Long quantity) {
        try {
            SessionCreateParams params =
                    SessionCreateParams.builder()
                            .setMode(SessionCreateParams.Mode.PAYMENT)
                            .setSuccessUrl("http://localhost:8081/success.html")
                            .setCancelUrl("http://localhost:8081/canceled.html")
                            .addLineItem(
                                    SessionCreateParams.LineItem
                                            .builder()
                                            .setPrice(priceId)
                                            .setQuantity(quantity)
                                            .build()
                            )
                            .setCustomer(customerId)
                            .setCurrency("usd")
                            .build();
            return Session.create(params, buildRequestOptions());

        } catch (StripeException se) {
            logger.error("Error creating session for customer : {} with price : {} ", customerId, priceId, se);
            throw new RuntimeException("PAYMENT_GATEWAY_ERROR");
        }
    }


    public ProductPriceDto createProduct(String productName, Long amount) {
        try {
            ProductCreateParams productParams = ProductCreateParams
                    .builder()
                    .setName(productName)
                    .setDescription(productName)
                    .setActive(true)
                    .build();

            Product product = Product.create(productParams, buildRequestOptions());

            PriceCreateParams priceParams =
                    PriceCreateParams
                            .builder()
                            .setProduct(product.getId())
                            .setUnitAmount(amount * 100L) //cents
                            .setCurrency("usd")
                            .setActive(true)
                            .build();

            Price price = Price.create(priceParams, buildRequestOptions());

            return new ProductPriceDto(price.getId(), product.getId());

        } catch (StripeException se) {
            logger.error("Error creating product ", se);
            throw new RuntimeException("PAYMENT_GATEWAY_ERROR");
        }
    }


  public String createCustomer(String name, String email) {
        try {
            CustomerCreateParams param = CustomerCreateParams
                    .builder()
                    .setName(name)
                    .setEmail(email)
                    .build();

            Customer customer = Customer.create(param, buildRequestOptions());

            return customer.getId();

        } catch (StripeException se) {
            logger.error("Error creating Customer ", se);
            throw new RuntimeException("PAYMENT_GATEWAY_ERROR");
        }
    }


    private RequestOptions buildRequestOptions(){
        return RequestOptions.builder().setApiKey(stripeBillingSecretKey).build();
    }
}














