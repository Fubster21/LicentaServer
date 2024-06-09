package ro.upt.etc.licenta.service;

import org.springframework.stereotype.Service;
import ro.upt.etc.licenta.repository.dto.SupplierResponseDTO;

import java.util.List;

@Service
public interface SupplierService {
    List<SupplierResponseDTO> getAllSuppliers();
    SupplierResponseDTO createSupplier(SupplierResponseDTO supplierResponseDTO);
    SupplierResponseDTO updateSupplier(Long id, SupplierResponseDTO supplierResponseDTO);
    void deleteSupplier(Long id);
    SupplierResponseDTO getSupplierById(Long id);
    List<SupplierResponseDTO> searchSuppliersByName(String name);
}
