package com.dvhlspringboot.testspringboot.Repositories;

import java.util.List;

import com.dvhlspringboot.testspringboot.Model.Product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long>{
    List<Product> findByProductName(String productName);
    List<Product> findByProductNameContaining(String productName);
}
