package org.example.Entity;

import jakarta.persistence.*;
import lombok.*;

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
    public String seller;
    @OneToMany
    @JoinColumn(name="seller_fk")
    public List<Product> productList;
}
