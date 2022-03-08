package com.dvhlspringboot.testspringboot.Repositories;

import java.util.List;

import com.dvhlspringboot.testspringboot.Model.Category;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {

    List<Category> findByCategoryName(String categorytName);
    
}
