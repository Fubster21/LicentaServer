package ro.upt.etc.licenta.repository.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private String name;
    private String description;
    private String colour;
    private Double price;
    private Integer stockQuantity;
    private String supplierName;
    private String image;
}
