package service.market.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import service.market.product.entity.Product;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class CreateProductDTO implements Serializable {
    @NotBlank(message = "Product name is required")
    @Size(min = 2, max = 100, message = "Product name must be between 2 and 100 characters")
    private String name;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;

    @JsonProperty("category_id")
    private Long categoryId;
}
