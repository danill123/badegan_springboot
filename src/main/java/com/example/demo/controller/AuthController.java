package com.example.demo.controller;

import com.example.demo.helpers.md5hash;
import com.example.demo.models.Users;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    UserRepository userRepository;

    @PostMapping
    public ResponseEntity<Users> checkLogin(@RequestBody Users user) {
        md5hash md5 = new md5hash();
        try {
            Users _user = userRepository.findByEmail(user.getEmail());
            if(_user == null) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }

            String passwd_md5 = md5.process_md5(user.getPassword());
            if(_user.getPassword().equals(passwd_md5)) {
                return new ResponseEntity<>(_user, HttpStatus.ACCEPTED);
            }

            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
