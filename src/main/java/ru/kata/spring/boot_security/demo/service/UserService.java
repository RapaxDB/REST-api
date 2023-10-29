package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    User findByUsername(String username);
    void addUser(User user);
    void deleteUser(Long id);
    User getUserById(Long id);
    void editUser (User user);
    List<User> getUsers();
    List<Role> getRoles();

}
