package ru.kata.spring.boot_security.demo.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.service.UserService;
import java.security.Principal;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/rest_api/admin")
public class AdminController {

    private UserService service;
    private RoleRepository roleRepository;
    @Autowired
    public AdminController (UserService service, RoleRepository roleRepository) {
        this.service = service;
        this.roleRepository = roleRepository;
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(service.getUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getUserById(id));
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getRoles() {
        return ResponseEntity.ok(roleRepository.findAll());
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
