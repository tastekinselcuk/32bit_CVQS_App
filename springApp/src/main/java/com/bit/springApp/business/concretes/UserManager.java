package com.bit.springApp.business.concretes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bit.springApp.business.abstracts.UserService;
import com.bit.springApp.domain.users.User;
import com.bit.springApp.dto.UserDTO;
import com.bit.springApp.repository.UserRepository;
import com.bit.springApp.security.config.JwtService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserManager implements UserService {

	  private final PasswordEncoder passwordEncoder;
	  private final JwtService jwtService;


    @Autowired
    private UserRepository userRepository;

    /**
     * Returns a list of all users in the system
     * 
     * @return List of User objects
     */
    public List<User> getAllUsers() {
        return userRepository.findByDeletedFalse();
    }
    
    /**
     * Returns a list of all user DTOs in the system
     * 
     * @return List of UserDTO objects
     */
    @Override
    public List<UserDTO> getAllUserDtos() {
        List<User> userList = userRepository.findByDeletedFalse();
        List<UserDTO> userDTOList = new ArrayList<>();
        for (User user : userList) {
            userDTOList.add(new UserDTO(user.getFirstname(), user.getLastname(), user.getEmail(), user.getRoles()));
        }
        return userDTOList;
    }

    /**
     * Returns a user DTO with the specified ID
     * 
     * @param id ID of the user to be returned
     * @return UserDTO object
     */
    @Override
    public UserDTO getUserDtoById(Integer id) {
        Optional<User> optionalUser = userRepository.findByIdAndDeletedFalse(id);
        User existingUser = optionalUser.orElseThrow(() -> new RuntimeException("User not found"));
        
        User user = optionalUser.get();
        return new UserDTO(user.getFirstname(), user.getLastname(), user.getEmail(), user.getRoles());
    }

    /**
     * Saves a new user to the system
     * 
     * @param user User object to be saved
     * @return User object that was saved
     */
    @Override
    public User saveUser(User user) {
        if (userRepository.existsByEmailAndDeletedFalse(user.getEmail())) {
            throw new RuntimeException("Email already exists!");
        }
        if (user.getPassword().length() < 8 || !user.getPassword().matches(".*[a-z].*") || !user.getPassword().matches(".*[A-Z].*")) {
            throw new RuntimeException("Password should contain at least one lowercase letter, one uppercase letter, and be at least 8 characters long!");
        }
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(user.getRoles());
            userRepository.save(user);
            jwtService.generateToken(user);
            return user;
        } catch (Exception e) {
            throw new RuntimeException("Error saving user", e);
        }
    }

    /**
     * Updates an existing user in the system
     * 
     * @param id ID of the user to be updated
     * @param user User object with updated information
     * @return Updated User object
     */
    @Override
    public User updateUser(Integer id, User user) {
        Optional<User> optionalUser = userRepository.findByIdAndDeletedFalse(id);
        User existingUser = optionalUser.orElseThrow(() -> new RuntimeException("User not found"));
        
        existingUser.setFirstname(user.getFirstname());
        existingUser.setLastname(user.getLastname());
        existingUser.setRoles(user.getRoles());
        return userRepository.save(existingUser);

    }

    /**
     * Changes the password of a user.
     * 
     * @param id The ID of the user whose password will be changed
     * @param password The new password
     * @return The user with the updated password
    */
    @Override
	public User changeUserPassword(Integer id, String password) {
        Optional<User> optionalUser = userRepository.findByIdAndDeletedFalse(id);
        User existingUser = optionalUser.orElseThrow(() -> new RuntimeException("User not found"));

	    if (password.length() < 8 || !password.matches(".*[a-z].*") || !password.matches(".*[A-Z].*")) {
	        throw new RuntimeException("Password should contain at least one lowercase letter, one uppercase letter, and be at least 8 characters long!");
	    }
	    try {
            User user = optionalUser.get();
		    user.setPassword(passwordEncoder.encode(password));
		    userRepository.save(user);

		    return user;
	    } catch (Exception e) {
            throw new RuntimeException("Error changing user password", e);
		}

	}

    /**
     * Soft deletes the user with the given ID.
     * 
     * @param id The ID of the user to be soft-deleted.
    */
	@Override
    public void softDeleteUser(Integer id) {
        Optional<User> optionalUser = userRepository.findByIdAndDeletedFalse(id);
        User existingUser = optionalUser.orElseThrow(() -> new RuntimeException("User not found"));
        try {
            existingUser.setDeleted(true);
            userRepository.save(existingUser);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting user", e);
        }
    }
	

}

