package org.example.Service;

import org.example.Entity.Product;
import org.example.Entity.Seller;
import org.example.Exception.ProductNotFoundException;
import org.example.Repository.ProductRepository;
import org.example.Repository.SellerRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import org.slf4j.LoggerFactory;

@Service

public class ProductService {
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
    ProductRepository productRepository;
    SellerRepository sellerRepository;

    @Autowired

    public ProductService(ProductRepository productRepository, SellerRepository sellerRepository) {
        this.productRepository = productRepository;
        this.sellerRepository = sellerRepository;
    }

    public List<Product> getAllProducts() {
        logger.info("Fetching all products");
        return productRepository.findAll();
    }

    public Product saveProduct(long id, Product p) throws ProductNotFoundException {
        logger.info("Saving Product with ID: {}", id);
        Optional<Seller> optional = sellerRepository.findById(id);
        Seller s;
        if (optional.isEmpty()) {
            logger.error("Seller Not Found");
            throw new ProductNotFoundException("Seller Not Found");
        } else {
            s = optional.get();
        }
        Product savedProduct = productRepository.save(p);
        s.getProductList().add(savedProduct);
        sellerRepository.save(s);
        return savedProduct;
    }

    public List<Product> getAllProductsByName(String name) {
        logger.info("Fetching all products with name: {}", name);
        return productRepository.findByName2(name);
    }

    public Product getById(long id) throws ProductNotFoundException {
        logger.info("Fetching Product with ID:{}", id);
        Optional<Product> p = productRepository.findById(id);
        if (p.isEmpty()) {
            throw new ProductNotFoundException("Product Not Found");
        } else {
            return p.get();
        }

    }

    public Product updateProduct(long id, Product updatedProduct) throws ProductNotFoundException {
        logger.info("Updating Product with ID: {}", id);
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            logger.error("Product Not Found");
            throw new ProductNotFoundException("Product Not Found");
        }
        Product existingProduct = optionalProduct.get();

        existingProduct.setName(updatedProduct.getName());
        existingProduct.setPrice(updatedProduct.getPrice());

        return productRepository.save(existingProduct);
    }

    public Product deleteProduct(long id) throws ProductNotFoundException {
        logger.info("Deleting Product with ID: {}", id);
        Optional<Product> optionalProduct = productRepository.findById(id);
        Product productToDelete;
        if (optionalProduct.isEmpty()) {
            logger.error("Product Not Found");
            throw new ProductNotFoundException("Product Not Found");
        } else {
            productToDelete = optionalProduct.get();
        }

        productRepository.delete(productToDelete);
        return productToDelete;
    }

}
