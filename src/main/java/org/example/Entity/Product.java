package org.example.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@ToString
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    public long id;
    @Column(nullable = false)
    public String name;
    @Column(nullable = false)
    public double price;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="seller_fk")
    @JsonIgnoreProperties("products")
    public Seller seller;

}