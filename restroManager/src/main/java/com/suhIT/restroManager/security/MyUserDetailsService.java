package com.suhIT.restroManager.security;


import com.suhIT.restroManager.dto.UserDTO;
import com.suhIT.restroManager.exception.AccountNotActivatedException;
import com.suhIT.restroManager.mapper.UserMapper;
import com.suhIT.restroManager.model.User;
import com.suhIT.restroManager.service.UserService;
import com.suhIT.restroManager.service.implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {


    private final UserServiceImpl service;

    @Autowired
    public MyUserDetailsService(UserServiceImpl userService) {
        this.service = userService;
    }
//    @Autowired
//    private UserMapper userMapper;

//    @Autowired
//    public MyUserDetailsService(UserService service, UserMapper userMapper) {
//        this.service = service;
//        this.userMapper = userMapper;
//
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final UserDTO userDTO = service.getUserByUsername(username);
        User user = User.builder().firstName(userDTO.getFirstName()).lastName(userDTO.getLastName())
                .email(userDTO.getEmail()).password(userDTO.getPassword()).username(userDTO.getUsername())
                .role(userDTO.getRole()).active(userDTO.isActive()).build();
//        User user = userMapper.toEntity(userDTO);
        if (!user.isActive()) {
            throw new AccountNotActivatedException();
        }

        UserDetails details = new UserDetails() {
            /**
             *
             */
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isEnabled() {

                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                // TODO Auto-generated method stub
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                // TODO Auto-generated method stub
                return true;
            }

            @Override
            public boolean isAccountNonExpired() {
                // TODO Auto-generated method stub
                return true;
            }

            @Override
            public String getUsername() {
                // TODO Auto-generated method stub
                return user.getUsername();
            }

            @Override
            public String getPassword() {

                return user.getPassword();
            }

            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {

				if (user.getRole() == null) {
					return new ArrayList<>();
				}
				final List<GrantedAuthority> authorities = Collections
						.singletonList(new SimpleGrantedAuthority(user.getRole().toString()));
				return authorities;
            }
        };

        return details;

    }

}
