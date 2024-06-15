package ro.upt.etc.licenta.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private String colour;
    private Double price;
    private Integer stockQuantity;
    private String supplierName;
    private String image;
    private List<CategoryDto> categories;
}
