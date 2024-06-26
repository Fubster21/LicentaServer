package ro.upt.etc.licenta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ro.upt.etc.licenta.repository.ProductRepository;
import ro.upt.etc.licenta.repository.UserRepository;
import ro.upt.etc.licenta.repository.dto.ProductDTO;
import ro.upt.etc.licenta.repository.dto.UserResponseDTO;
import ro.upt.etc.licenta.repository.entity.Product;
import ro.upt.etc.licenta.repository.entity.User;
import ro.upt.etc.licenta.repository.entity.UserRole;
import ro.upt.etc.licenta.service.ProductService;
import ro.upt.etc.licenta.service.UserService;
import ro.upt.etc.licenta.service.impl.AuthenticationService;
import ro.upt.etc.licenta.service.impl.ProductServiceImpl;

@SpringBootApplication
public class LicentaApplication {
    private static final Logger sLogger = LoggerFactory.getLogger(LicentaApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(LicentaApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner init(ProductService productService) {
//        return args -> {productService.createProduct(ProductDTO.builder()
//                        .name("Cola")
//                        .description("Colac")
//                        .colour("Colac")
//                        .price(1.)
//                        .stockQuantity(2)
//                        .supplierName("Io")
//                        .image("Colac")
//                .build());};
//    }

//    @Bean
//    CommandLineRunner init(UserRepository userRepository) {
////        sLogger.debug();
//        return args -> {
//            userRepository.save(User.builder()
//                    .username("user")
//                    .password("password")
//                    .firstName("test")
//                    .lastName("test")
//                    .phone("1234567890")
//                    .address("123 test")
//                    .role(UserRole.STANDARD)
//                    .build()
//            );
//        };
//    }



}
