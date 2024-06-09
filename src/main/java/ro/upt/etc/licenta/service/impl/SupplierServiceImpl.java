package ro.upt.etc.licenta.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ro.upt.etc.licenta.repository.SupplierRepository;
import ro.upt.etc.licenta.repository.dto.SupplierResponseDTO;
import ro.upt.etc.licenta.repository.entity.Supplier;
import ro.upt.etc.licenta.service.SupplierService;
import ro.upt.etc.licenta.service.exception.ResourceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<SupplierResponseDTO> getAllSuppliers() {
        return supplierRepository.findAll().stream()
                .map(supplier -> modelMapper.map(supplier, SupplierResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public SupplierResponseDTO createSupplier(SupplierResponseDTO supplierResponseDTO) {
        Supplier supplier = modelMapper.map(supplierResponseDTO, Supplier.class);
        supplier = supplierRepository.save(supplier);
        return modelMapper.map(supplier, SupplierResponseDTO.class);
    }

    @Override
    public SupplierResponseDTO updateSupplier(Long id, SupplierResponseDTO supplierResponseDTO) {
        Supplier existingSupplier = supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with id " + id));
        modelMapper.map(supplierResponseDTO, existingSupplier);
        existingSupplier = supplierRepository.save(existingSupplier);
        return modelMapper.map(existingSupplier, SupplierResponseDTO.class);
    }

    @Override
    public void deleteSupplier(Long id) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with id " + id));
        supplierRepository.delete(supplier);
    }

    @Override
    public SupplierResponseDTO getSupplierById(Long id) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with id " + id));
        return modelMapper.map(supplier, SupplierResponseDTO.class);
    }

    @Override
    public List<SupplierResponseDTO> searchSuppliersByName(String name) {
        return supplierRepository.findByNameContainingIgnoreCase(name).stream()
                .map(supplier -> modelMapper.map(supplier, SupplierResponseDTO.class))
                .collect(Collectors.toList());
    }
}
