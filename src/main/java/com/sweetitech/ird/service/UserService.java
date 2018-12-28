package com.sweetitech.ird.service;

import com.sweetitech.ird.domain.dto.requestDto.UserRequestDto;
import com.sweetitech.ird.domain.User;
import com.sweetitech.ird.common.Exception.UserNotFoundException;
import com.sweetitech.ird.domain.dto.responseDto.UserResponseDto;
import com.sweetitech.ird.pageable.UserResponsePage;

import java.util.List;

public interface UserService {

    User readByUsername(String username) throws UserNotFoundException;

    boolean isPasswordMatches(User user, String password) throws Exception;

    User addUser(UserRequestDto userRequestDto) throws Exception;

    UserResponsePage getAllUsers(Integer page);

    User deleteUser(String userId);

    User findByUserId(String userID);

    User findById(Long id);

    User updateUser(UserRequestDto dto);

    List<UserResponseDto> userList();

}