package service.market.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.market.product.dto.CreateProductDTO;
import service.market.product.dto.FilterProductDTO;
import service.market.product.dto.UpdateProductDTO;
import service.market.product.entity.Product;
import service.market.product.entity.ProductCategory;
import service.market.product.repository.ProductCategoryRepository;
import service.market.product.repository.ProductRepository;

import java.util.Collection;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public Collection<?> getAllProducts(FilterProductDTO filter) {
        Collection<Product> products = this.productRepository.findAll();
        return products;
    }

    @Override
    public Product createProduct(CreateProductDTO product) {
        ProductCategory productCategory = null;
        if (product.getCategoryId() != null) {
            productCategory = this.productCategoryRepository.findById(product.getCategoryId()).orElseThrow(() -> new IllegalArgumentException("Category not found"));
        }

        Product newProduct = Product.builder().name(product.getName()).description(product.getDescription()).productCategory(productCategory).build();
        return this.productRepository.save(newProduct);
    }

    @Override
    public Product getProductDetail(Long id) {
        Optional<Product> productDetail = this.productRepository.findById(id);
        return productDetail.map(product -> Product.builder().id(product.getId()).name(product.getName()).description(product.getDescription()).build()).orElse(null);
    }

    @Override
    public Product updateProduct(UpdateProductDTO product) {
        Product newProduct = Product.builder().name(product.getName()).description(product.getDescription()).build();
        return this.productRepository.save(newProduct);
    }

    @Override
    public Optional<Product> deleteProduct(Product product) {
        return Optional.empty();
    }
}

