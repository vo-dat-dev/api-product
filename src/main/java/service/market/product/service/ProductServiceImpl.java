package service.market.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.market.product.dto.CreateProductDTO;
import service.market.product.dto.UpdateProductDTO;
import service.market.product.entity.Product;
import service.market.product.repository.ProductRepository;
import java.util.Collection;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Collection<?> getAllProducts() {
        return this.productRepository.findAll();
    }

    @Override
    public Product createProduct(CreateProductDTO product) {
        return this.productRepository.save(product);
    }

    @Override
    public Optional<Product> getProductDetail(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product updateProduct(UpdateProductDTO product) {
        Product newProduct = Product.builder()
                .name(product.getName())
                .description(product.getDescription())
                .build();
        return this.productRepository.save(newProduct);
    }

    @Override
    public Optional<Product> deleteProduct(Product product) {
        return Optional.empty();
    }
}

