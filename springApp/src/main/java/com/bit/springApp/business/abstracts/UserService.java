package com.bit.springApp.business.abstracts;

import java.util.List;

import com.bit.springApp.domain.users.User;
import com.bit.springApp.dto.UserDTO;

public interface UserService {

    List<User> getAllUsers();
    
    List<UserDTO> getAllUserDtos();
    
    UserDTO getUserDtoById(Integer id);
    
    User saveUser(User user);
    
    User updateUser(Integer id, User user);
    
    User changeUserPassword(Integer id, String password);
    
    void softDeleteUser(Integer id);
}
