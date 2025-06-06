package service.market.product;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import service.market.product.service.ProductCategoryService;
import service.market.product.service.ProductCategoryServiceImpl;
import service.market.product.service.ProductService;
import service.market.product.service.ProductServiceImpl;

@Configuration
public class AppConfig {
    @Bean
    public ProductService productService() {
        return new ProductServiceImpl();
    }

    @Bean
    public ProductCategoryService productCategoryService() {
        return new ProductCategoryServiceImpl();
    }
}
