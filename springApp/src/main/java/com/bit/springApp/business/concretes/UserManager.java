package com.bit.springApp.business.concretes;

import java.util.ArrayList;
import java.util.List;

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

    public List<User> getAllActiveUsers() {
        return userRepository.findByDeletedFalse();
    }
    
    @Override
    public List<UserDTO> getAllActiveUserDtos() {
        List<User> userList = userRepository.findByDeletedFalse();
        List<UserDTO> userDTOList = new ArrayList<>();
        for (User user : userList) {
            userDTOList.add(new UserDTO(user.getFirstname(), user.getLastname(), user.getEmail(), user.getRoles()));
        }
        return userDTOList;
    }

    @Override
    public UserDTO getActiveUserDtoById(Integer id) {
        try {
            User user = userRepository.findByIdAndDeletedFalse(id);
            if (user == null) {
                return null;
            }
            return new UserDTO(user.getFirstname(), user.getLastname(), user.getEmail(), user.getRoles());
        } catch (Exception e) {
            throw new RuntimeException("Error getting user by id", e);
        }
    }

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

    @Override
    public User updateActiveUser(Integer id, User user) {
	    user = userRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("User not found"));
        try {
            User existingUser = userRepository.findByIdAndDeletedFalse(id);
            existingUser.setFirstname(user.getFirstname());
            existingUser.setLastname(user.getLastname());
            existingUser.setRoles(user.getRoles());
            return userRepository.save(existingUser);
        } catch (Exception e) {
            throw new RuntimeException("Error updating user", e);
        }
    }

    
	/**
	 * Updates the password of the user with the given ID
	 *
	 * @param id the ID of the user to update
	 * @param password the new password of the user
	 * @return the updated user information
	 */
	public User changeActiveUserPassword(Integer id, String password) {
	    User user = userRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("User not found"));

	    if (password.length() < 8 || !password.matches(".*[a-z].*") || !password.matches(".*[A-Z].*")) {
	        throw new RuntimeException("Password should contain at least one lowercase letter, one uppercase letter, and be at least 8 characters long!");
	    }

	    user.setPassword(passwordEncoder.encode(password));
	    userRepository.save(user);

	    return user;
	}


	@Override
    public void softDeleteActiveUser(Integer id) {
	    User user = userRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("User not found"));
        try {
            User existingUser = userRepository.findByIdAndDeletedFalse(id);
            existingUser.setDeleted(true);
            userRepository.save(existingUser);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting user", e);
        }
    }
	

}

