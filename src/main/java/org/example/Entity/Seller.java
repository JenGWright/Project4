package org.example.Entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.example.Validator.NotInteger;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@ToString

public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    public long id;
    @Column(nullable = false)
    @NotBlank(message = "Seller must be a non-empty String")
    @NotInteger
    public String seller;
    @OneToMany
    @JoinColumn(name="seller_fk")
    public List<Product> productList;
}
