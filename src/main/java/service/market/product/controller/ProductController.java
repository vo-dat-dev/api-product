package service.market.product.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.market.product.dto.CreateProductDTO;
import service.market.product.dto.UpdateProductDTO;
import service.market.product.entity.Product;
import service.market.product.service.ProductService;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Product", description = "Product API")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    @Operation(summary = "Get all product")
    public String getProducts() {
        return productService.getAllProducts().toString();
    }

    @PostMapping("/product")
    public ResponseEntity<?> createProduct(@RequestBody CreateProductDTO product) {
        Product newProduct = this.productService.createProduct(product);
        return ResponseEntity.ok(newProduct);
    }

    @PutMapping("/product")
    public ResponseEntity<Product> updateProduct(@RequestBody UpdateProductDTO product) {
        Product updateProduct = this.productService.updateProduct(product);
        return ResponseEntity.ok(updateProduct);
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<Optional<Product>> getProductDetail(@PathVariable Long productId) {
        Optional<Product> product = productService.getProductDetail(productId);
        if (product.isEmpty()) return ResponseEntity.badRequest().body(null);
        return ResponseEntity.ok(product);
    }
}
