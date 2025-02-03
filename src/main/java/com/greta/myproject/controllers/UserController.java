package com.greta.myproject.controllers;


import com.greta.myproject.daos.UserDao;
import com.greta.myproject.entities.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }



    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUser() {
        return ResponseEntity.ok(userDao.findAll());
    }


    @GetMapping("/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userDao.findByEmail(email));
    }



    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userDao.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }


    @PutMapping("/{email}")
    public ResponseEntity<User> updateUser(@PathVariable String email, @RequestBody User user) {
        User updatedUser = userDao.update(email, user);
        return ResponseEntity.ok(updatedUser);
    }



    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteUser(@PathVariable String email) {
        if (userDao.delete(email)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
