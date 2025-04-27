package service.market.product.service;

import service.market.product.dto.CreateProductCategoryDTO;
import service.market.product.dto.UpdateProductCategoryDTO;
import service.market.product.entity.ProductCategory;

import java.util.Collection;
import java.util.Optional;

public interface ProductCategoryService {
//     Define methods for product category service
//     For example:
     Collection<?> getAllCategories();
     ProductCategory createProductCategory(CreateProductCategoryDTO productCategory);
     Optional<ProductCategory> getCategoryDetail(Long id);
     ProductCategory updateCategory(UpdateProductCategoryDTO category);
     Optional<ProductCategory> deleteCategory(ProductCategory category);

}
