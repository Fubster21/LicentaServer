package ro.upt.etc.licenta.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.upt.etc.licenta.repository.CategoryRepository;
import ro.upt.etc.licenta.repository.ProductRepository;
import ro.upt.etc.licenta.repository.SupplierRepository;
import ro.upt.etc.licenta.repository.dto.CategoryDto;
import ro.upt.etc.licenta.repository.dto.ProductDTO;
import ro.upt.etc.licenta.repository.entity.Category;
import ro.upt.etc.licenta.repository.entity.Image;
import ro.upt.etc.licenta.repository.entity.Product;
import ro.upt.etc.licenta.repository.entity.Supplier;
import ro.upt.etc.licenta.service.ProductService;
import ro.upt.etc.licenta.service.exception.ResourceNotFoundException;

import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);
    @Autowired
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(product -> {
                    ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
                    productDTO.setImage(getImageBase64(product));
                    return productDTO;
                })
                .collect(Collectors.toList());
    }

    private String getImageBase64(Product product) {
        if (product.getImage() == null) { return null; }
        return Base64.getEncoder().encodeToString(product.getImage().getImage());
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
//        var supplier = Supplier.builder()
//                .name(productDTO.getName())
//                .build();
//        var supplier = supplierRepository.findById(1L).orElseThrow();

//        Set<Category> categories = productDTO.getCategoryNames().stream()
//                .map(categoryName -> categoryRepository.findByName(categoryName)
//                        .orElseThrow(() -> new ResourceNotFoundException("Category not found with name " + categoryName)))
//                .collect(Collectors.toSet());

        var categorySet = productDTO.getCategories().stream()
                .map(CategoryDto::getId)
                .map(Long::valueOf)
                .map(categoryRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());

        Image image = Image.builder()
                .image(Base64.getDecoder().decode(productDTO.getImage()))
                .fileName("product_image")
                .fileType("image/jpeg")
                .build();

        var product = Product.builder()
                .name(productDTO.getName())
                .description(productDTO.getDescription())
//                .colour(productDTO.getColour())
                .price(productDTO.getPrice())
                .stockQuantity(productDTO.getStockQuantity())
//                .supplier(supplier)
                .categories(categorySet)
                .image(image)
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

    @Override
    public List<ProductDTO> getProductsByCategory(Long catId) {
        var category = categoryRepository.findById(catId).orElseThrow();
        var categoryDto = modelMapper.map(category, CategoryDto.class);
        log.info("querying by category: {}", category.getName());

        var entities = productRepository.findByCategories(category);

//        entities.forEach(e -> {
//            log.info("entity {} has categories {}", e.getName(), e.getCategories());
//        });

        var prodList = entities.stream()
                .map(product -> {
                    ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
                    productDTO.setImage(getImageBase64(product));
                    return productDTO;
                })
                .peek(productDTO -> productDTO.setCategories(List.of(categoryDto)))
                .toList();
        return prodList;
    }

//    @Override
//    public List<ProductDTO> getProductsByCategory(String categoryName) {
//        var category = categoryRepository.findByName(categoryName)
//                .orElseThrow(() -> new ResourceNotFoundException("Category not found with name " + categoryName));
//        return productRepository.findByCategoriesContainingIgnoreCase(Set.of(category)).stream()
//                .map(prod -> modelMapper.map(prod, ProductDTO.class))
//                .collect(Collectors.toList());
//        return category.getProducts().stream()
//                .map(product -> modelMapper.map(product, ProductDTO.class))
//                .collect(Collectors.toList());
//    }
}
