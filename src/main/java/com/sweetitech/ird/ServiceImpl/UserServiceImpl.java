package com.sweetitech.ird.ServiceImpl;

import com.sweetitech.ird.Domain.DTO.RequestDto.UserDTO;
import com.sweetitech.ird.Domain.User;
import com.sweetitech.ird.Mapper.UserMapper;
import com.sweetitech.ird.Repository.UserRepository;
import com.sweetitech.ird.Service.UserService;
import com.sweetitech.ird.common.Exception.UserNotFoundException;
import com.sweetitech.ird.common.Util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

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
        return userRepository.save(userMapper.map(userDTO));
    }
}