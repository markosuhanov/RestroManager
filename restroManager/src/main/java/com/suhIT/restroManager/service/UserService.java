package com.suhIT.restroManager.service;

import com.suhIT.restroManager.dto.UserDTO;
import com.suhIT.restroManager.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserDTO createUser(UserDTO userDTO);

    UserDTO updateUser(String username, UserDTO updatedUserDTO);

    List<UserDTO> getAllUsers();

    UserDTO getUserById(UUID id);

    UserDTO getUserByUsername(String username);


    void deleteUser(String username);

    void validateUniqueUsername(String username);

}
