package com.organicbazar.user.dto;

import com.organicbazar.user.entity.Role;
import lombok.Data;

@Data
public class UserDto {
    private String username;
    private String email;
    private String password;
    private Role role;
}
