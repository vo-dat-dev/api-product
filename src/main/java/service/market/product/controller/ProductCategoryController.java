package service.market.product.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.market.product.entity.ProductCategory;
import service.market.product.service.CreateProductCategoryDTO;
import service.market.product.service.ProductCategoryService;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Product Category", description = "Product Category API")
public class ProductCategoryController {
    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/product-categories")
    @Operation(summary = "Get all product categories")
    public Collection<?> getProductCategories() {
        return this.productCategoryService.getAllCategories();
    }

    @PostMapping("/product-category")
    @Operation(summary = "create product category")
    public ResponseEntity<?> createProductCategory(@RequestBody CreateProductCategoryDTO productCategory) {
        ProductCategory newProductCategory = this.productCategoryService.createProductCategory(productCategory);
        return ResponseEntity.ok(newProductCategory);
    }
}
