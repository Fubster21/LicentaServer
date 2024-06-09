package ro.upt.etc.licenta.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "products")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private Long id;

    private String name;

    @Column(name = "description", length = 255)
    private String description;
    private String colour;
    private Double price;
    private int stockQuantity;

    @OneToOne
    private Image image;


//    @JsonIgnoreProperties("ideas")
//    @ManyToOne
//    @JoinColumn(name = "order_id", referencedColumnName = "order_id", nullable = false)
//    private Order order;

    @ManyToOne
    @JoinColumn(name = "supplier_id", referencedColumnName = "supplier_id", nullable = false)
    private Supplier supplier;
}
