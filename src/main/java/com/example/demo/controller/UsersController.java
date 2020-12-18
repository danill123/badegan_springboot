package com.example.demo.controller;

import com.example.demo.helpers.md5hash;
import com.example.demo.models.Users;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    UserRepository userRepository;

    @GetMapping
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    /* simple note for add & edit user
    {
       "id" : "5fce34d2a9b08137fbd39000",
       ^----> if you want to add user just remove the id property from json,
          but if you want to edit the data just add id property to json with id the data

       "name" : "Danill Yudhistira Setiawan",
       "lastname" : "Setiawan is the best",
       "password" : "watch dogs hacker is your weaphon"
    }
     */
    @PostMapping
    public ResponseEntity<List<Users>> AddUser(@RequestBody Users users) {
        try{
            List<Users> _user_check = userRepository.DoesEmailExist(users.getEmail());
            if(_user_check.toArray().length < 1) {
                md5hash md5hash = new md5hash();
                users.setPassword(md5hash.process_md5(users.getPassword()));
                Users _user = userRepository.save(users);
                return new ResponseEntity<>(HttpStatus.CREATED);
            }

            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") String id) {
        try {
            userRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
