package ro.upt.etc.licenta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.upt.etc.licenta.repository.ProductRepository;
import ro.upt.etc.licenta.repository.dto.ProductDTO;
import ro.upt.etc.licenta.repository.entity.Category;
import ro.upt.etc.licenta.repository.entity.Product;

import java.util.List;

@Service
public interface ProductService {

    List<ProductDTO> getAllProducts();

    ProductDTO createProduct(ProductDTO productDTO);
    ProductDTO updateProduct(Long id, ProductDTO productDTO);
    Void deleteProduct(Long id);
    ProductDTO getProductById(Long id);
    List<ProductDTO> searchProductsByName(String name);
//    List<ProductDTO> getProductsByCategory(String categoryName);
    List<ProductDTO> getProductsByCategory(Long catId);
}