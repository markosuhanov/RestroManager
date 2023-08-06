package com.suhIT.restroManager.controller;


import com.suhIT.restroManager.dto.UserDTO;
import com.suhIT.restroManager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public ResponseEntity<List<UserDTO>> getAll() {
        List<UserDTO> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    @GetMapping("/{username}")
    public ResponseEntity<Object> getByUsername(@PathVariable("username") String username) {

        UserDTO user = userService.getUserByUsername(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody UserDTO dto) {
        try {
            UserDTO userDTO = userService.createUser(dto);
            return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/update/{username}")
    public ResponseEntity<Object> update(@PathVariable("username") String username,
                                         @RequestBody UserDTO updatedUserDTO) {

        UserDTO updatedUser = userService.updateUser(username, updatedUserDTO);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Object> delete(@PathVariable("username") String username) {

        userService.deleteUser(username);
        return new ResponseEntity<>("User successfully deleted", HttpStatus.OK);
    }

    @PutMapping("activate/{username}")
    public ResponseEntity<Object> activate(@PathVariable("username") String username) {
        userService.activateUser(username);
        return new ResponseEntity<>("User successfully activated again!", HttpStatus.OK);
    }

    @GetMapping("/allWaiterUsernames")
    public ResponseEntity<List<String>> getAllWaiterUsernames() {
        List<String> allUsernames = this.userService.getAllWaiterUsernames();
        return new ResponseEntity<>(allUsernames, HttpStatus.OK);

    }



}
