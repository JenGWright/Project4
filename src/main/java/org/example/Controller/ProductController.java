package org.example.Controller;



import org.example.Entity.Product;
import org.example.Exception.ProductNotFoundException;
import org.example.Service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    ProductService productService;
    public ProductController(ProductService productService){

        this.productService = productService;
    }

    @GetMapping(value="/product", params = "name")
    public ResponseEntity<List<Product>> getAllProductsByName(@RequestParam String name){
        logger.info("Fetch all Products");
        List<org.example.Entity.Product> products;
        if (name == null) {
            products = productService.getAllProducts();
        }else{
            products = productService.getAllProductsByName(name);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping(value = "product")
    public ResponseEntity<List<Product>> getAllProduct(){
        List<Product> products;
        products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }


    @PostMapping("seller/{id}/product")
    public ResponseEntity<Product> addProduct(@RequestBody Product p, @PathVariable long id) throws Exception {
        Product product = productService.saveProduct(id, p);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @GetMapping("product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable long id){
        try{
            Product p = productService.getById(id);
            return new ResponseEntity<>(p, HttpStatus.OK);
        }catch (ProductNotFoundException e ){
            logger.error("Invalid ID");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("product/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product p, @PathVariable long id){
        try{
            Product product =productService.updateProduct(id, p);
            return new ResponseEntity<>(p, HttpStatus.OK);
        }catch (ProductNotFoundException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable long id){
        try{
            Product product =productService.deleteProduct(id);
            String message = "Product is deleted Successfully!";
            return ResponseEntity.ok(message);
        }catch (ProductNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.OK);
        }

    }

}
