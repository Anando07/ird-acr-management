package com.sweetitech.ird.Service;

import com.sweetitech.ird.Domain.DTO.RequestDto.UserDTO;
import com.sweetitech.ird.Domain.User;
import com.sweetitech.ird.common.Exception.UserNotFoundException;

public interface UserService {

    User readByUsername(String username) throws UserNotFoundException;

    boolean isPasswordMatches(User user, String password) throws Exception;

    User addUser(UserDTO userDTO) throws Exception;

}