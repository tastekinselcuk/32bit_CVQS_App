package com.bit.springApp.business.abstracts;

import java.util.List;

import com.bit.springApp.domain.users.User;
import com.bit.springApp.dto.UserDTO;

public interface UserService {

    List<User> getAllActiveUsers();
    List<UserDTO> getAllActiveUserDtos();
    UserDTO getActiveUserDtoById(Integer id);
    User saveUser(User user);
    User updateActiveUser(Integer id, User user);
    User changeActiveUserPassword(Integer id, String password);
    void softDeleteActiveUser(Integer id);
}
