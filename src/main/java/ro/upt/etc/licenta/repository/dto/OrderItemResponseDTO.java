package ro.upt.etc.licenta.repository.dto;

import lombok.Data;

@Data
public class OrderItemResponseDTO {
    private ProductDTO product;
    private Integer quantity;
}
