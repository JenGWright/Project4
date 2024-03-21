package org.example.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    @Column(nullable = false)
    public String name;
    @Column(nullable = false)
    @NotNull
    public double price;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="seller_fk")
    @JsonIgnoreProperties("productList")
    public Seller seller;

}