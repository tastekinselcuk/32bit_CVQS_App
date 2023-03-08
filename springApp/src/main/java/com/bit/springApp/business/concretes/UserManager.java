package com.bit.springApp.business.concretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit.springApp.business.abstracts.UserService;
import com.bit.springApp.domain.users.User;
import com.bit.springApp.dto.UserDTO;
import com.bit.springApp.repository.UserRepository;

@Service
public class UserManager implements UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findByDeletedFalse();
    }
    
    public List<UserDTO> getAllUserDto() {
        List<User> userList = userRepository.findByDeletedFalse();
        List<UserDTO> userDTOList = new ArrayList<>();
        for (User user : userList) {
            userDTOList.add(new UserDTO(user.getFirstname(), user.getLastname(), user.getEmail(), user.getRole()));
        }
        return userDTOList;
    }

    public User getUserById(Integer id) {
        return userRepository.findByIdAndDeletedFalse(id);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Integer id, User user) {
        User existingUser = userRepository.findByIdAndDeletedFalse(id);
        existingUser.setFirstname(user.getFirstname());
        existingUser.setLastname(user.getLastname());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setRole(user.getRole());
        return userRepository.save(existingUser);
    }

    public void softDeleteUser(Integer id) {
        User existingUser = userRepository.findByIdAndDeletedFalse(id);
        existingUser.setDeleted(true);
        userRepository.save(existingUser);
    }

}

