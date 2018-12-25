package com.sweetitech.ird.service;

import com.sweetitech.ird.domain.dto.requestDto.UserDTO;
import com.sweetitech.ird.domain.User;
import com.sweetitech.ird.common.Exception.UserNotFoundException;
import com.sweetitech.ird.pageable.UserResponsePage;

import java.util.List;

public interface UserService {

    User readByUsername(String username) throws UserNotFoundException;

    boolean isPasswordMatches(User user, String password) throws Exception;

    User addUser(UserDTO userDTO) throws Exception;

    UserResponsePage getAllUsers(Integer page);

}