package com.dvhlspringboot.testspringboot.Repositories;

import java.util.List;

import com.dvhlspringboot.testspringboot.Model.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product,Long>{
    List<Product> findByProductName(String productName);
    List<Product> findByProductNameContaining(String productName);

    // @Query(value = "SELECT p FROM product p JOIN p.category c")
    // List<Product> findAllWithCategory();
}
