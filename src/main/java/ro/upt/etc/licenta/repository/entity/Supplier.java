package ro.upt.etc.licenta.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "suppliers")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "supplier_id")
    private Long id;

    private String name;
    private String address;
    private String phone;
    private String email;

    @OneToMany(mappedBy = "supplier")
    private List<Product> products;
}
