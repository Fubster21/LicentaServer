package ro.upt.etc.licenta.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.upt.etc.licenta.repository.dto.SupplierResponseDTO;
import ro.upt.etc.licenta.service.SupplierService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/suppliers")
@RequiredArgsConstructor
public class SupplierController {
    private final SupplierService supplierService;

    @GetMapping("/all")
    public ResponseEntity<List<SupplierResponseDTO>> getAllSuppliers() {
        return ResponseEntity.ok(supplierService.getAllSuppliers());
    }

    @PostMapping("/new")
    public ResponseEntity<SupplierResponseDTO> createSupplier(@RequestBody SupplierResponseDTO  SupplierResponseDTO ) {
        return ResponseEntity.ok(supplierService.createSupplier(SupplierResponseDTO ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupplierResponseDTO> updateSupplier(@PathVariable Long id, @RequestBody SupplierResponseDTO  SupplierResponseDTO ) {
        return ResponseEntity.ok(supplierService.updateSupplier(id, SupplierResponseDTO ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable Long id) {
        supplierService.deleteSupplier(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierResponseDTO> getSupplierById(@PathVariable Long id) {
        return ResponseEntity.ok(supplierService.getSupplierById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<SupplierResponseDTO>> searchSuppliers(@RequestParam String name) {
        return ResponseEntity.ok(supplierService.searchSuppliersByName(name));
    }
}
