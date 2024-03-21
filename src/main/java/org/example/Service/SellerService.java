package org.example.Service;
import org.example.Entity.Seller;
import org.example.Repository.SellerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SellerService {
    private static final Logger logger = LoggerFactory.getLogger(SellerService.class);
    SellerRepository sellerRepository;
    @Autowired
    public SellerService(SellerRepository sellerRepository){

        this.sellerRepository = sellerRepository;
    }
    public List<Seller> getAllSellers(){
        logger.info("Fetching all Sellers");
        return sellerRepository.findAll();
    }

    public Seller saveSeller (Seller s) {
     logger.info("Saving Sellers");
        return sellerRepository.save(s);
    }
}
