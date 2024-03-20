package org.example.Repository;

import org.example.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {


    /*Painting savedPainting = paintingRepository.save(p);
        a.getPaintings().add(savedPainting);
        artistRepository.save(a);
        return savedPainting;*/
    //named query
    List<Product> findByName(String name);
    ///JPQL query
    //SQL dialect agnostic
    //directly maps to an ORM entity

    @Query("from Product where name=:name")
    List<Product> findByName2(@Param("name")String name);
}
