package com.sweetitech.ird.serviceImpl;

import com.sweetitech.ird.common.Util.PageAttribute;
import com.sweetitech.ird.domain.dto.requestDto.UserDTO;
import com.sweetitech.ird.domain.User;
import com.sweetitech.ird.domain.dto.responseDto.UserResponseDto;
import com.sweetitech.ird.mapper.requestMapper.UserRequestMapper;
import com.sweetitech.ird.mapper.responseMapper.UserResponseMapper;
import com.sweetitech.ird.pageable.UserResponsePage;
import com.sweetitech.ird.repository.UserRepository;
import com.sweetitech.ird.service.UserService;
import com.sweetitech.ird.common.Exception.UserNotFoundException;
import com.sweetitech.ird.common.Util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRequestMapper userRequestMapper;

    @Autowired
    UserResponseMapper responseMapper;

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User readByUsername(String username) throws UserNotFoundException {
        System.out.println("From username in impl:" + username);
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean isPasswordMatches(User user, String password) throws Exception {
        /**
         * Check if password matches with shaencoder, if matches encode it with bicrypt and save to the database.
         */
        if (PasswordUtil.encryptPassword(password).equals(user.getPassword())) {
            user.setPassword(PasswordUtil.encryptPassword(password));
            user = userRepository.save(user);
        }

        return PasswordUtil.getPasswordEncoder().matches(password, user.getPassword());
    }


    @Override
    public User addUser(UserDTO userDTO) throws Exception {
        return userRepository.save(userRequestMapper.map(userDTO));
    }

    @Override
    public UserResponsePage getAllUsers(Integer page) {
        Page<User> userList = userRepository.findAll(new PageRequest(page, PageAttribute.PAGE_SIZE));
        List<UserResponseDto> userDtoList = new ArrayList<>();
        for(User user : userList.getContent())
        {
            if(user.isDeleted() == false) {
                userDtoList.add(responseMapper.map(user));
            }
        }
        return new UserResponsePage(userDtoList,userList);
    }

    @Override
    public User deleteUser(String userId) {
        User user = userRepository.findByUserId(userId);
        user.setDeleted(true);
        return userRepository.save(user);
    }

    @Override
    public User findByUserId(String userID) {
        return userRepository.findByUserId(userID);
    }
}