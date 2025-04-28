package service.market.product.dto;

import lombok.Builder;

@Builder
public class FilterProductDTO {
    private Long id;
    private String name;
    private String description;
}
