package com.bit.springApp.controller.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bit.springApp.business.abstracts.UserService;
import com.bit.springApp.domain.users.User;
import com.bit.springApp.dto.UserDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/getAllUserDto")
    public List<UserDTO> getAllUserDto() {
        return userService.getAllUserDto();
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @userSecurity.checkUserId(authentication,#id)")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
        User createdUser = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @userSecurity.checkUserId(authentication,#id)")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @Valid @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user);
        return ResponseEntity.ok().body(updatedUser);
    }

    @PutMapping("/softDelete/{id}")
    @PreAuthorize("hasRole('ADMIN') or @userSecurity.checkUserId(authentication,#id)")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        userService.softDeleteUser(id);
        return ResponseEntity.ok().build();
    }
    
}
