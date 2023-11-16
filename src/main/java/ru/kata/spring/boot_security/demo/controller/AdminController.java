package ru.kata.spring.boot_security.demo.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;
import java.security.Principal;
import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService service;
    @Autowired
    public AdminController (UserService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAdminPage() {
        return ResponseEntity.ok(service.getUsers());
    }

    @PostMapping("/newUser")
    public ResponseEntity<HttpStatus> createUser(@RequestBody User user) {
        service.addUser(user);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PostMapping("/edit")
    public ResponseEntity<HttpStatus> editUser(@RequestBody User user) {
        service.editUser(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> removeUserById(@PathVariable("id") Long id) {
        service.deleteUser(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
