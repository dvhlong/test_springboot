package com.dvhlspringboot.testspringboot.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.dvhlspringboot.testspringboot.Model.Category;
import com.dvhlspringboot.testspringboot.Model.ResponseObject;
import com.dvhlspringboot.testspringboot.Repositories.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = "/category")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;
    @GetMapping("") 
    List<Category> getAllCategories(){
        return  categoryRepository.findAll();
    }
    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> findById(@PathVariable long id) {
        Optional<Category> foundCategory=categoryRepository.findById(id);
        if(foundCategory.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK","Da tim thay",foundCategory));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Fail","Ko tim thay",""));
        }
    }
    @PostMapping("/add")
    ResponseEntity<ResponseObject> addProduct(@RequestBody Category newCategory){
        List<Category> foundCategory= categoryRepository.findByCategoryName(newCategory.getCategoryName());
        if(foundCategory.size()>0){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Fail","ten dang nhap da ton tai",""));
        } else {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK","Da Dang ky thanh cong",categoryRepository.save(newCategory)));
        }
    }
    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteProduct(@PathVariable long id){
        Optional<Category> foundCategory=categoryRepository.findById(id);
        if(foundCategory.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Fail", "Product can xoa khong ton tai", ""));
        } else {
            categoryRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Da xoa thanh cong", foundCategory));
        }
    }
    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateProduct(@PathVariable long id,@RequestBody Category newCategory){

        Optional<Category> updatedCategory=categoryRepository.findById(id).map(category ->{
            category.setCategoryName(newCategory.getCategoryName());
            return categoryRepository.save(category);
        });
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK","Product updated",updatedCategory));
    }
}
