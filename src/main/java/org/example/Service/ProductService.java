package org.example.Service;

import org.example.Entity.Product;
import org.example.Entity.Seller;
import org.example.Exception.ProductNotFoundException;
import org.example.Repository.ProductRepository;
import org.example.Repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service

public class ProductService {
    ProductRepository productRepository;
    SellerRepository sellerRepository;
    @Autowired

    public ProductService(ProductRepository productRepository, SellerRepository sellerRepository){
        this.productRepository = productRepository;
        this.sellerRepository = sellerRepository;
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product saveProduct(long id, Product p) throws ProductNotFoundException {
        Optional<Seller> optional = sellerRepository.findById(id);
        Seller s;
        if(optional.isEmpty()){
            throw new ProductNotFoundException("Seller Not Found");
        }else{
            s = optional.get();
        }
        Product savedProduct = productRepository.save(p);
        s.getProducts().add(savedProduct);
        sellerRepository.save(s);
        return savedProduct;
    }

    public List<Product> getAllProductsByName(String name) {
        return productRepository.findByName2(name);
    }

    public Product getById(long id) throws ProductNotFoundException {
        Optional<Product> p = productRepository.findById(id);
        if(p.isEmpty()){
            throw new ProductNotFoundException("Product Not Found");
        }else{
            return p.get();
        }
    }
}
