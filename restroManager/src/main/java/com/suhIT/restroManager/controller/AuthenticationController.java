package com.suhIT.restroManager.controller;

import com.suhIT.restroManager.dto.LoginDTO;
import com.suhIT.restroManager.dto.UserDTO;
import com.suhIT.restroManager.security.TokenUtils;
import com.suhIT.restroManager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/login")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private UserService userService;

    @Autowired
    private TokenUtils tokenUtils;
//
//    public AuthenticationController(AuthenticationManager authenticationManager, UserDetailsService userDetailsService,
//                                    UserService userService, TokenUtils tokenUtils) {
//        this.authenticationManager = authenticationManager;
//        this.userDetailsService = userDetailsService;
//        this.userService = userService;
//        this.tokenUtils = tokenUtils;
//    }


    @PostMapping()
    public ResponseEntity<?> login(@RequestBody() LoginDTO loginDTO) {
        try {
            // Perform the authentication
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(),
                    loginDTO.getPassword()
            );
            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Reload user details so we can generate token
            UserDetails details = userDetailsService.loadUserByUsername(loginDTO.getUsername());

            UserDTO user = userService.getLoggedUser();

            user.setJWTToken(tokenUtils.generateToken(details));


            return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }


    }

}

