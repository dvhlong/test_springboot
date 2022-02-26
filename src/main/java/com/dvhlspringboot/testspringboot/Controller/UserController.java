package com.dvhlspringboot.testspringboot.Controller;

import java.util.List;
import java.util.Optional;

import com.dvhlspringboot.testspringboot.Model.ResponseObject;
import com.dvhlspringboot.testspringboot.Model.User;
import com.dvhlspringboot.testspringboot.Repositories.UserRepository;

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
@RequestMapping(path = "/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @GetMapping("")
    List<User> getAllUsers(){
        return userRepository.findAll();
        
    }
    @GetMapping("/{uid}")
    ResponseEntity<ResponseObject> findByUid(@PathVariable long uid) {
        Optional<User> foundUser=userRepository.findById(uid);
        if(foundUser.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK","Da tim thay",foundUser));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Fail","Ko tim thay",""));
        }
        //return userRepository.findById(uid).orElseThrow(() -> new RuntimeException("Ko tim thay user co uid la "+uid));
    }
    @PostMapping("/register")
    ResponseEntity<ResponseObject> registerUser(@RequestBody User newUser){
        List<User> foundUser= userRepository.findByUsername(newUser.getUsername());
        if(foundUser.size()>0){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Fail","ten dang nhap da ton tai",""));
        } else {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK","Da Dang ky thanh cong",userRepository.save(newUser)));
        }
    }
    @DeleteMapping("/{uid}")
    ResponseEntity<ResponseObject> deleteUser(@PathVariable long uid) {
        Optional<User> foundUser=userRepository.findById(uid);
        if(foundUser.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Fail","User can xoa ko ton tai",""));
        } else {
            userRepository.deleteById(uid);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK","Da xoa user thanh cong",foundUser));
        }
    }
    @PostMapping("/login")
    ResponseEntity<ResponseObject> loginUser(@RequestBody User logindata) {
        List<User> foundUser=userRepository.findByUsernameAndPassword(logindata.getUsername(), logindata.getPassword());
        if(foundUser.size()>0){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK","Dang nhap thanh cong",foundUser.get(0)));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Fail","Ten tai khoan hoac mat khau ko dung",""));
        }
    }
    @PutMapping("/{uid}")
    ResponseEntity<ResponseObject> changePassword(@RequestBody String newPass,@PathVariable long uid) {
        Optional<User> updatedUser = userRepository.findById(uid).map(user -> {
            user.setPassword(newPass);
            return userRepository.save(user);
        });
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK","Doi mat khau thanh cong",updatedUser));
    }
    @PostMapping("/search")
    List<User> searchUsername(@RequestBody String key) {
        return userRepository.findByUsernameContaining(key);
    }
}
