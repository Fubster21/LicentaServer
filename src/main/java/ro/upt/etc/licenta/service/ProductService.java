package ro.upt.etc.licenta.service;

import org.springframework.stereotype.Service;
import ro.upt.etc.licenta.repository.dto.ProductDTO;

import java.util.List;

@Service
public interface ProductService {
    List<ProductDTO> getAllProducts();

    ProductDTO createProduct(ProductDTO productDTO);
    ProductDTO updateProduct(Long id, ProductDTO productDTO);
    Void deleteProduct(Long id);
    ProductDTO getProductById(Long id);
    List<ProductDTO> searchProductsByName(String name);
}