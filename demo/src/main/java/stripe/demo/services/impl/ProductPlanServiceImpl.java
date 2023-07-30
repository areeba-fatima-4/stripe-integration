package stripe.demo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stripe.demo.domain.models.ProductPlan;
import stripe.demo.domain.models.ProductsBought;
import stripe.demo.dtos.ProductPlanDto;
import stripe.demo.dtos.ProductsBoughtDto;
import stripe.demo.payment.gateways.StripeClient;
import stripe.demo.repositories.ProductPlanRepository;
import stripe.demo.repositories.ProductsBoughtRepository;
import stripe.demo.services.ProductPlanService;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Transactional
public class ProductPlanServiceImpl implements ProductPlanService {

    @Autowired StripeClient stripeClient;
    @Autowired ProductPlanRepository productPlanRepository;
    @Autowired ProductsBoughtRepository productsBoughtRepository;


    @Override
    public ProductPlanDto addProductPlan(ProductPlanDto productPlanDto) {

    ProductPlan productPlan = new ProductPlan();
    productPlan.setId(UUID.randomUUID());
    productPlan.setProductName(productPlanDto.getProductName());
    productPlan.setAmount(productPlanDto.getAmount());

    var productPriceIdPair = stripeClient.createProduct(productPlan.getProductName(), productPlan.getAmount());

    productPlan.setProductID(productPriceIdPair.getProductId());
    productPlan.setPriceId(productPriceIdPair.getPriceId());

    var plan = productPlanRepository.save(productPlan);
    productPlanDto.setId(plan.getId());
    return productPlanDto;
    }

}
