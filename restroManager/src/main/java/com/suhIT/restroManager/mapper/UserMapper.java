package com.suhIT.restroManager.mapper;

import com.suhIT.restroManager.dto.UserDTO;
import com.suhIT.restroManager.exception.UserNotFoundException;
import com.suhIT.restroManager.model.User;
import com.suhIT.restroManager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<User, UserDTO> {

    private final UserRepository userRepository;

    @Autowired
    public UserMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO toDTO(User user) {
        return UserDTO.builder().firstName(user.getFirstName()).lastName(user.getLastName())
                .username(user.getUsername()).email(user.getEmail()).password(user.getPassword()).role(user.getRole())
                .active(user.isActive()).build();

    }
    public User toEntity(UserDTO userDTO) {
        User user = userRepository.findByUsername(userDTO.getUsername())
                .orElseThrow(() -> new UserNotFoundException(HttpStatus.NOT_FOUND, "User with username: " + userDTO.getUsername() + " not found!"));

        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setRole(userDTO.getRole());
        user.setActive(userDTO.isActive());

        return user;
    }


}

