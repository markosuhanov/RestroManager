package com.suhIT.restroManager.service.implementation;

import com.suhIT.restroManager.dto.UserDTO;
import com.suhIT.restroManager.exception.NoSuchElementException;
import com.suhIT.restroManager.exception.RoleNotFoundException;
import com.suhIT.restroManager.exception.UserAlreadyExists;
import com.suhIT.restroManager.exception.UserNotFoundException;
import com.suhIT.restroManager.mapper.UserMapper;
import com.suhIT.restroManager.model.Role;
import com.suhIT.restroManager.model.User;
import com.suhIT.restroManager.repository.UserRepository;
import com.suhIT.restroManager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {


    private UserRepository userRepository;

    private UserMapper userMapper;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    @Override
    public UserDTO createUser(UserDTO userDTO) {
        validateUniqueUsername(userDTO.getUsername());
        EnumSet<Role> allowedRoles = EnumSet.of(Role.ADMIN, Role.MANAGER, Role.COOK, Role.WAITER, Role.BARTENDER, Role.ROOM_MANAGER, Role.DIRECTOR);
        if(!allowedRoles.contains(userDTO.getRole())){
            throw new RoleNotFoundException(HttpStatus.BAD_REQUEST,
                    "Role " + userDTO.getRole().name() + " doesnt exist!"
            );
        }

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String pass = passwordEncoder.encode(userDTO.getPassword());

        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(pass);
        user.setActive(userDTO.isActive());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setUsername(userDTO.getUsername());
        user.setRole(userDTO.getRole());
        UserDTO userDTOnew = userMapper.toDTO(userRepository.save(user));
        return userDTOnew;
    }

    @Override
    public UserDTO updateUser(String username, UserDTO updatedUserDTO) {

        // It will never be null because I always update the existing one.
        User existingUser = userRepository.findByUsername(username).orElseThrow(
                () -> new UserNotFoundException(HttpStatus.NOT_FOUND,
                        "User with username: '" + username + "' not found!"
                ));

        String newUsername = updatedUserDTO.getUsername();
        updatedUserDTO.setUsername(username);
        User updatedUser = userMapper.toEntity(updatedUserDTO);
        updatedUser.setUsername(newUsername);
        validateUniqueUsername(updatedUser.getUsername(), existingUser.getId());
        existingUser.setFirstName(updatedUser.getFirstName());
        existingUser.setLastName(updatedUser.getLastName());
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setEmail(updatedUser.getEmail());

        existingUser.setPassword(updatedUser.getPassword());
        UserDTO savedUser =userMapper.toDTO(userRepository.save(existingUser));
        savedUser.setPassword(null);
        return savedUser;
    }



    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        if (users.size() == 0) {
            throw new NoSuchElementException(HttpStatus.NOT_FOUND, "Users not found.");
        }
        return users.stream().map(userMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException(HttpStatus.NOT_FOUND, "User with id: " + id + " not found!");
        }
        return userMapper.toDTO(user.get());
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new UserNotFoundException(HttpStatus.NOT_FOUND,
                        "User with username: '" + username + "' not found!"
                ));
        return userMapper.toDTO(user);
    }


    @Override
    public void deleteUser(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new UserNotFoundException(HttpStatus.NOT_FOUND,
                        "User with username: '" + username + "' not found!"
                ));
        user.setActive(false);
        userRepository.save(user);
    }

    @Override
    public void activateUser(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new UserNotFoundException(HttpStatus.NOT_FOUND,
                        "User with username: '" + username + "' not found!"
                ));
        user.setActive(true);
        userRepository.save(user);
    }

    @Override
    public UserDTO getLoggedUser() {
        try {
            UserDTO userDTO = getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
            return userDTO;
        } catch (Exception e) {
            if (e instanceof NullPointerException ) {
                return null;
            }
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<String> getAllWaiterUsernames() {
        return this.userRepository.findUsernamesByWaiterRole();
    }


    // Validate if the username already exists
    public void validateUniqueUsername(String username) {
        Optional<User> existingUser = userRepository.findByUsername(username);
        existingUser.ifPresent(user -> {
            throw new UserAlreadyExists(HttpStatus.BAD_REQUEST,
                    "User with username: '" + username + "' already exists!"
            );
        });
    }

    // Validate if the username is unique, except for the user being updated.
    private void validateUniqueUsername(String username, Long currentUserId) {
        Optional<User> existingUser = userRepository.findByUsernameAndIdNot(username, currentUserId);
        existingUser.ifPresent(user -> {
            throw new UserAlreadyExists(HttpStatus.BAD_REQUEST, "Username is not available!");
        });
    }
}
