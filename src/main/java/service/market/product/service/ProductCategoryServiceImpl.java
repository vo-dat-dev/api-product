package service.market.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.market.product.dto.CreateProductCategoryDTO;
import service.market.product.dto.UpdateProductCategoryDTO;
import service.market.product.entity.ProductCategory;
import service.market.product.repository.ProductCategoryRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public Collection<?> getAllCategories() {
        return List.of();
    }

    @Override
    public ProductCategory createProductCategory(CreateProductCategoryDTO category) {
        ProductCategory newProductCategory = ProductCategory.builder().name(category.getName()).build();
        return this.productCategoryRepository.save(newProductCategory);
    }

    @Override
    public Optional<ProductCategory> getCategoryDetail(Long id) {
        return Optional.empty();
    }

    @Override
    public ProductCategory updateCategory(UpdateProductCategoryDTO category) {
        ProductCategory newProductCategory = ProductCategory.builder().name(category.getName()).build();
        return this.productCategoryRepository.save(newProductCategory);
    }

    @Override
    public Optional<ProductCategory> deleteCategory(ProductCategory category) {
        return Optional.empty();
    }
}
