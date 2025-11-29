package com.organicbazar.user.service;

import com.organicbazar.user.dto.UserDto;

public interface UserService {
    String register(UserDto dto);
    String login(String email, String password);
}
