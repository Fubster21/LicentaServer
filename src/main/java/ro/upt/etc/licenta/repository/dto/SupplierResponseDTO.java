package ro.upt.etc.licenta.repository.dto;

import lombok.Data;

@Data
public class SupplierResponseDTO {
    private Long id;
    private String name;
    private String address;
    private String email;
    private String phone;
}
