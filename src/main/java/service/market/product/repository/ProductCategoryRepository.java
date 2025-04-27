package service.market.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import service.market.product.entity.ProductCategory;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
}
