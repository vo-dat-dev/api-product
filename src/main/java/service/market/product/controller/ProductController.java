package service.market.product.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.hibernate.sql.ast.tree.expression.Collation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.market.product.dto.CreateProductDTO;
import service.market.product.dto.FilterProductDTO;
import service.market.product.dto.UpdateProductDTO;
import service.market.product.entity.Product;
import service.market.product.service.ProductService;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Product", description = "Product API")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    @Operation(summary = "Get all product")
    public ResponseEntity<Collection<?>> getProducts(@RequestParam(required = false) Long id, @RequestParam(required = false) String name, @RequestParam(required = false) String description) {
        FilterProductDTO.FilterProductDTOBuilder filter = FilterProductDTO.builder();
        if (id != null) {
            filter.id(id);
        }

        if (name != null) {
            filter.name(name);
        }

        if (description != null) {
            filter.description(description);
        }

        Collection<?> products = this.productService.getAllProducts(filter.build());
        return ResponseEntity.ok(products);
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
    public ResponseEntity<Product> getProductDetail(@PathVariable Long productId) {
        Product product = productService.getProductDetail(productId);
        return ResponseEntity.ok(product);
    }
}
