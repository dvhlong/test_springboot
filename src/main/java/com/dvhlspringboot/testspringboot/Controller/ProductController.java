package com.dvhlspringboot.testspringboot.Controller;

import java.util.List;
import java.util.Optional;

import com.dvhlspringboot.testspringboot.Model.Product;
import com.dvhlspringboot.testspringboot.Model.ResponseObject;
import com.dvhlspringboot.testspringboot.Model.Search;
import com.dvhlspringboot.testspringboot.Repositories.ProductRepository;

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
@RequestMapping(path = "/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @GetMapping("")
    List<Product> getAllProducts(){
        return productRepository.findAll();
    }
    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> findById(@PathVariable long id) {
        Optional<Product> foundProduct=productRepository.findById(id);
        if(foundProduct.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK","Da tim thay",foundProduct));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Fail","Ko tim thay",""));
        }
    }
    @PostMapping("/add")
    ResponseEntity<ResponseObject> addProduct(@RequestBody Product newProduct){
        List<Product> foundProduct= productRepository.findByProductName(newProduct.getProductName());
        if(foundProduct.size()>0){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Fail","ten dang nhap da ton tai",""));
        } else {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK","Da Dang ky thanh cong",productRepository.save(newProduct)));
        }
    }
    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteProduct(@PathVariable long id){
        Optional<Product> foundProduct=productRepository.findById(id);
        if(foundProduct.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Fail", "Product can xoa khong ton tai", ""));
        } else {
            productRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Da xoa thanh cong", foundProduct));
        }
    }
    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateProduct(@PathVariable long id,@RequestBody Product newProduct){

        Optional<Product> updatedProduct=productRepository.findById(id).map(product ->{
            product.setProductName(newProduct.getProductName());
            product.setPrice(newProduct.getPrice());
            product.setAmount(newProduct.getAmount());
            return productRepository.save(product);
        });
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK","Product updated",updatedProduct));
    }
    @PostMapping("/search")
    ResponseEntity<ResponseObject> searchProduct(@RequestBody Search key){
        List<Product> foundProduct=productRepository.findByProductNameContaining(key.getKey());
        if (foundProduct.size()>0) {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK","Found",foundProduct));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Fail","NotFound",foundProduct));
        }
    }
}
