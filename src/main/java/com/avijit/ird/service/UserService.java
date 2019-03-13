package com.avijit.ird.service;

import com.avijit.ird.domain.dto.UserDTO;
import com.avijit.ird.common.Exception.UserNotFoundException;
import com.avijit.ird.domain.User;

import java.util.List;

public interface UserService {

    User readByUsername(String username) throws UserNotFoundException;

    boolean isPasswordMatches(User user, String password) throws Exception;

    User addUser(UserDTO userRequestDto) throws Exception;

    User deleteUser(String userId);

    User findByUserId(String userID);

    User findById(Long id);

    User updateUser(UserDTO dto);

    List<UserDTO> userList();

    void resetPassword(Long userId, String password) throws Exception;





}