package com.suhIT.restroManager.dto;

import com.suhIT.restroManager.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    @NotBlank(message = "First name is required")
    private String firstName;
    @NotBlank(message = "Last name is required")
    private String lastName;
    @NotBlank(message = "Username is required")
    @Size(min = 6, message = "The username must contain at least 6 characters!")
    private String username;
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    @NotBlank(message = "Password is required")
    private String password;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Role is required")
    private Role role;
    @NotNull(message = "User activity active is required")
    private boolean active;
    /*
    * TODO boolean atTheJob
    * when taking orders, waiter who is atTheJob and has the least ordersr
    * takes that one
    * Implement in User.class too
    * */
}