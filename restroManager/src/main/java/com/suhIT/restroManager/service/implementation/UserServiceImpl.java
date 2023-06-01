package com.suhIT.restroManager.service.implementation;

import com.suhIT.restroManager.dto.UserDTO;
import com.suhIT.restroManager.exception.UserNotFoundException;
import com.suhIT.restroManager.mapper.UserMapper;
import com.suhIT.restroManager.model.User;
import com.suhIT.restroManager.repository.UserRepository;
import com.suhIT.restroManager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final Validator validator;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }


    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = User.builder().firstName(userDTO.getFirstName()).lastName(userDTO.getLastName())
                .username(userDTO.getUsername()).email(userDTO.getEmail()).password(userDTO.getPassword())
                .role(userDTO.getRole()).active(userDTO.isActive()).build();
        //validateUser(user); // Validate constraints defined in model
        //validateUniqueUsername(user.getUsername());
        User createdUser = userRepository.save(user);
        return userMapper.toDTO(createdUser);
    }

    @Override
    public UserDTO updateUser(String username, UserDTO updatedUserDTO) {
        // It will never be null because I always update the existing one.
        User existingUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        User updatedUser = userMapper.toEntity(updatedUserDTO);
        validateUser(updatedUser);
        validateUniqueUsername(updatedUser.getUsername(), existingUser.getId());
        existingUser.setFirstName(updatedUser.getFirstName());
        existingUser.setLastName(updatedUser.getLastName());
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPassword(updatedUser.getPassword());
        User savedUser = userRepository.save(existingUser);
        return userMapper.toDTO(savedUser);
    }


    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(userMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        return userMapper.toDTO(user);
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        return userMapper.toDTO(user);
    }


    @Override
    public void deleteUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

//        // Check if there are any unpaid bills associated with the user
//        List<Bill> unpaidBills = billRepository.findByWaiterAndPaid(user, false);
//        if (!unpaidBills.isEmpty()) {
//            throw new IllegalStateException("Cannot delete user with unpaid bills");
//        }

        // Set the user as inactive
        user.setActive(false);
        userRepository.save(user);
    }

//    private void validateUser(User user) {
//        Set<ConstraintViolation<User>> violations = validator.validate(user);
//        if (!violations.isEmpty()) {
//            StringBuilder sb = new StringBuilder();
//            for (ConstraintViolation<User> violation : violations) {
//                sb.append(violation.getMessage()).append("; ");
//            }
//            String errorMessage = sb.toString();
//            // Log the error message
//           // logger.error(errorMessage);
//            // Alternatively, you can throw a different type of exception or handle the error accordingly
//            throw new IllegalArgumentException("Validation failed: " + errorMessage);
//        }
//    }


    //TODO void startJob(String username) -> atTheJob -> true

    //TODO void endJob(String username) -> atTheJob -> false

    //Validate user based on model defined validations
    private void validateUser(User user) {
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<User> violation : violations) {
                sb.append(violation.getMessage()).append("; ");
            }
            throw new IllegalArgumentException(sb.toString());
        }
    }

    // Validate if the username already exists
    @Override
    public void validateUniqueUsername(String username) {
        Optional<User> existingUser = userRepository.findByUsername(username);
        existingUser.ifPresent(user -> {
            throw new IllegalArgumentException("Username already exists");
        });
    }

    // Validate if the username is unique, except for the user being updated.
    private void validateUniqueUsername(String username, UUID currentUserId) {
        Optional<User> existingUser = userRepository.findByUsernameAndIdNot(username, currentUserId);
        existingUser.ifPresent(user -> {
            throw new IllegalArgumentException("Username already exists");
        });
    }
}
