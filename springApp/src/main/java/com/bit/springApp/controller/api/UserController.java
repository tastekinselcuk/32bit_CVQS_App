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

/**
 * Rest API for managing users.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    /**
     * Constructor for UserController
     * 
     * @param userService the user service to be used
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Returns a list of all users
     * 
     * @return a list of all users
     */
    @GetMapping("/getAllUsers")
    public ResponseEntity<?> getAllUsers() {
        List<User> users = userService.getAllActiveUsers();
        return ResponseEntity.ok().body(users);
    }

    /**
     * Returns a list of all users as UserDTOs
     * 
     * @return a list of all users as UserDTOs
     */
    @GetMapping("/getAllUserDto")
    public ResponseEntity<?> getAllUserDto() {
        List<UserDTO> users = userService.getAllActiveUserDtos();
        return ResponseEntity.ok().body(users);
    }
    
    /**
     * Returns a specific user by ID
     * 
     * @param id the ID of the user to retrieve
     * @return a ResponseEntity containing the user with the given ID, or a 404 Not Found status if no user exists with the given ID
     */
    @GetMapping("/getUserById/{id}")
    @PreAuthorize("hasRole('ADMIN') or @userSecurity.checkUserId(authentication,#id)")
    public ResponseEntity<?> getUserById(@PathVariable Integer id) {
    	UserDTO userDTO = userService.getActiveUserDtoById(id);
        return ResponseEntity.ok().body(userDTO);

    }

    /**
     * Adds a new user
     * 
     * @param user the user to add
     * @return a ResponseEntity containing the newly created user with a 201 Created status
     */
    @PostMapping("/saveUser")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addUser(@Valid @RequestBody User user) {
    	try {
            User createdUser = userService.saveUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    	} catch (RuntimeException e) {
            String errorMessage = e.getMessage();
            return ResponseEntity.badRequest().body(errorMessage);
        }


    }

    /**
     * Updates an existing user with the given ID
     * 
     * @param id the ID of the user to update
     * @param user the updated user information
     * @return a ResponseEntity containing a success message if the user was updated successfully, or a 404 Not Found status if no user exists with the given ID
     */
    @PutMapping("/updateUser/{id}")
    @PreAuthorize("hasRole('ADMIN') or @userSecurity.checkUserId(authentication,#id)")
    public ResponseEntity<String> updateUser(@PathVariable Integer id, @Valid @RequestBody User user) {
        User updatedUser = userService.updateActiveUser(id, user);
        return ResponseEntity.ok("User updated successfully");
    }
    
    /**
     * Changes the password for the user with the given ID.
     *
     * @param id the ID of the user whose password will be changed.
     * @param password the new password to be set for the user.
     * @return a ResponseEntity containing a success message if the password was updated successfully, or a 404 Not Found status if no user exists with the given ID.
     */
    @PutMapping("/changePassword/{id}")
    @PreAuthorize("hasRole('ADMIN') or @userSecurity.checkUserId(authentication,#id)")
    public ResponseEntity<String> changePassword(@PathVariable Integer id, @RequestBody String newPassword) {
        try {
            userService.changeActiveUserPassword(id, newPassword);
            return ResponseEntity.ok("Password updated successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    /**
     * Soft delete a user by ID.
     *
     * @param id the ID of the user to delete
     * @return a ResponseEntity with no content if the delete was successful, or a 404 Not Found status if no user exists with the given ID
     */
    @PutMapping("/softDeleteUser/{id}")
    @PreAuthorize("hasRole('ADMIN') or @userSecurity.checkUserId(authentication,#id)")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        userService.softDeleteActiveUser(id);
        return ResponseEntity.ok().build();
    }
}

