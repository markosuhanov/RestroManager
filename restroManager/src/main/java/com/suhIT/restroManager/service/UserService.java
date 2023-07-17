package com.suhIT.restroManager.service;

import com.suhIT.restroManager.dto.UserDTO;
import com.suhIT.restroManager.model.User;

import java.util.List;

public interface UserService {

    UserDTO createUser(UserDTO userDTO);

    UserDTO updateUser(String username, UserDTO updatedUserDTO);

    List<UserDTO> getAllUsers();

    UserDTO getUserById(Long id);

    UserDTO getUserByUsername(String username);


    void deleteUser(String username);

    void activateUser(String username);

    UserDTO getLoggedUser();

}
