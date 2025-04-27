package service.market.product.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@Entity(name = "product")
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Represents a product in the system")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the product", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Column(nullable = false)
    @Schema(description = "Name of the product", example = "Smartphone", required = true)
    private String name;

    @Column(nullable = true)
    @Schema(description = "Description of the product", example = "A high-end smartphone with advanced features", required = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @JsonIgnore
    private ProductCategory productCategory;
}