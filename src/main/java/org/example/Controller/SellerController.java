package org.example.Controller;


import jakarta.validation.Valid;
import org.example.Entity.Seller;
import org.example.Service.SellerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SellerController {
    SellerService sellerService;
    public SellerController(SellerService sellerService){

        this.sellerService = sellerService;
    }

    @GetMapping("/seller")
    public ResponseEntity<List<Seller>> getAllSeller(){
        List<Seller> sellers = sellerService.getAllSellers();
        return new ResponseEntity<>(sellers, HttpStatus.OK);

    }

    @PostMapping("/seller")
    public ResponseEntity<?> addSeller(@Valid @RequestBody Seller s, BindingResult result){
        if (result.hasErrors()){
            return ResponseEntity.badRequest().body("Invalid Input");}
        Seller seller = sellerService.saveSeller(s);
        return new ResponseEntity<>(seller, HttpStatus.CREATED);
    }
}
