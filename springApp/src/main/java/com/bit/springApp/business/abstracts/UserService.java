package com.bit.springApp.business.abstracts;

import java.util.List;

import com.bit.springApp.domain.users.User;
import com.bit.springApp.dto.UserDTO;

public interface UserService {

    List<User> getAllUsers();
    List<UserDTO> getAllUserDto();
    User getUserById(Integer id);
    User addUser(User user);
    User updateUser(Integer id, User user);
    void softDeleteUser(Integer id);
}
