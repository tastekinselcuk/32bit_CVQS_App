package com.bit.springApp.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

    /**
     * Constructor for UserController
     * 
     * @param userService the user service to be used
     */
	@Autowired
    private UserService userService;

    /**
     * Returns a list of all users.
     * 
     * @return a ResponseEntity containing a list of all users
     */
    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {
    	return this.userService.getAllUsers();
    }

    /**
     * Returns a list of all users as UserDTOs.
     * 
     * @return a ResponseEntity containing a list of all users as UserDTOs
     */
    @GetMapping("/getAllUserDtos")
    public List<UserDTO> getAllUserDtos() {
    	return this.userService.getAllUserDtos();
    }
    
    /**
     * Returns a specific user by ID.
     * 
     * @param id the ID of the user to retrieve
     * @return a ResponseEntity containing the user with the given ID, or a 500 Internal Server Error status if an unexpected error occurs.
     */
    @GetMapping("/getUserDtoById/{id}")
    @PreAuthorize("hasRole('ADMIN') or @userSecurity.checkUserId(authentication,#id)")
    public ResponseEntity<?> getUserDtoById(@PathVariable Integer id) {
    	try {
        	UserDTO userDTO = userService.getUserDtoById(id);
            return ResponseEntity.ok().body(userDTO);
    	} catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}


    }

    /**
     * Adds a new user
     * 
     * @param user the user to add
     * @return a ResponseEntity containing the newly created user with a 201 Created status, or a 400 Bad Request status if the request is invalid
     */
    @PostMapping("/saveUser")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> saveUser(@Valid @RequestBody User user) {
    	try {
            User createdUser = userService.saveUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    	} catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    /**
     * Updates an existing user with the given ID
     * 
     * @param id the ID of the user to update
     * @param user the updated user information
     * @return a ResponseEntity containing a success message if the user was updated successfully, or a 500 Internal Server Error status if an unexpected error occurs.
     */
    @PutMapping("/updateUser/{id}")
    @PreAuthorize("hasRole('ADMIN') or @userSecurity.checkUserId(authentication,#id)")
    public ResponseEntity<String> updateUser(@PathVariable Integer id, @Valid @RequestBody User user) {
    	try {
            User updatedUser = userService.updateUser(id, user);
            return ResponseEntity.ok("User updated successfully");
    	} catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
    }
    
    /**
     * Changes the password for the user with the given ID.
     *
     * @param id the ID of the user whose password will be changed.
     * @param password the new password to be set for the user.
     * @return a ResponseEntity containing a success message if the password was updated successfully, or a 500 Internal Server Error status if an unexpected error occurs.
     */
    @PutMapping("/changeUserPassword/{id}")
    @PreAuthorize("hasRole('ADMIN') or @userSecurity.checkUserId(authentication,#id)")
    public ResponseEntity<String> changeUserPassword(@PathVariable Integer id, @RequestBody String password) {
        try {
            userService.changeUserPassword(id,password);
            return ResponseEntity.ok("Password updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    /**
     * Soft delete a user by ID.
     *
     * @param id the ID of the user to delete
     * @return a ResponseEntity containing a success message if the delete was successful, or a 500 Internal Server Error status if an unexpected error occurs.
     */
    @PutMapping("/softDeleteUser/{id}")
    @PreAuthorize("hasRole('ADMIN') or @userSecurity.checkUserId(authentication,#id)")
    public ResponseEntity<String> softDeleteUser(@PathVariable Integer id) {
    	try {
            userService.softDeleteUser(id);
            return ResponseEntity.ok("User deleted with soft delete.");
    	} catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
    }
}

