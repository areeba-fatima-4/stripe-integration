package stripe.demo.services;

import stripe.demo.dtos.ProductPlanDto;
import stripe.demo.dtos.ProductsBoughtDto;

public interface ProductPlanService {
    public ProductPlanDto addProductPlan(ProductPlanDto productPlanDto);

}
