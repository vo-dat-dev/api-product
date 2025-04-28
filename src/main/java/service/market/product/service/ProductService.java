package service.market.product.service;

import service.market.product.dto.CreateProductDTO;
import service.market.product.dto.UpdateProductDTO;
import service.market.product.entity.Product;

import java.util.Collection;
import java.util.Optional;

public interface ProductService {
    Collection<?> getAllProducts();
    Product createProduct(CreateProductDTO product);
    Product getProductDetail(Long id);
    Product updateProduct(UpdateProductDTO product);
    Optional<Product> deleteProduct(Product product);
}
