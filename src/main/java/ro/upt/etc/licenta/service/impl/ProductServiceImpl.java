package ro.upt.etc.licenta.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ro.upt.etc.licenta.repository.ProductRepository;
import ro.upt.etc.licenta.repository.SupplierRepository;
import ro.upt.etc.licenta.repository.dto.ProductDTO;
import ro.upt.etc.licenta.repository.entity.Product;
import ro.upt.etc.licenta.repository.entity.Supplier;
import ro.upt.etc.licenta.service.ProductService;
import ro.upt.etc.licenta.service.exception.ResourceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final SupplierRepository supplierRepository;
    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .toList();
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        var supplier = Supplier.builder()
                .name(productDTO.getName())
                .build();
        supplier = supplierRepository.save(supplier);

        var product = Product.builder()
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .stockQuantity(productDTO.getStockQuantity())
                .supplier(supplier)
                .build();

        return modelMapper.map(productRepository.save(product), ProductDTO.class);
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
        modelMapper.map(productDTO, existingProduct);
        existingProduct = productRepository.save(existingProduct);
        return modelMapper.map(existingProduct, ProductDTO.class);
    }

    @Override
    public Void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
        productRepository.delete(product);
        return null;
    }

    @Override
    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
        return modelMapper.map(product, ProductDTO.class);
    }

    @Override
    public List<ProductDTO> searchProductsByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name).stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }
}
