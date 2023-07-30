package stripe.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import stripe.demo.dtos.ProductPlanDto;
import stripe.demo.services.ProductPlanService;

@RestController
public class ProductPlanController {

    @Autowired ProductPlanService productPlanService;

    @PostMapping("/add-product-plan")
    public ResponseEntity<ProductPlanDto> addProductPlan(
            @RequestBody ProductPlanDto productPlanDto) {
        var result = productPlanService.addProductPlan(productPlanDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
