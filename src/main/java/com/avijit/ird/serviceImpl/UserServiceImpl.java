package com.avijit.ird.serviceImpl;

import com.avijit.ird.common.Util.PasswordUtil;
import com.avijit.ird.domain.dto.UserDTO;
import com.avijit.ird.mapper.UserMapper;
import com.avijit.ird.common.Exception.UserNotFoundException;
import com.avijit.ird.domain.Role;
import com.avijit.ird.domain.User;
import com.avijit.ird.repository.UserRepository;
import com.avijit.ird.service.RoleService;
import com.avijit.ird.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    RoleService roleService;

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
    public User addUser(UserDTO userRequestDto) throws Exception {
        return userRepository.save(userMapper.requestMapper(userRequestDto));
    }

/*    @Override
    public UserResponsePage getAllUsers(Integer page) {
        Page<User> userList = userRepository.findAll(new PageRequest(page, PageAttribute.PAGE_SIZE));
        List<UserResponseDto> userDtoList = new ArrayList<>();
        for (User user : userList.getContent()) {
            if (user.isDeleted() == false) {
                userDtoList.add(userMapper.responseMapper(user));
            }
        }
        return new UserResponsePage(userDtoList, userList);
    }*/

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

    @Override
    public User findById(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public User updateUser(UserDTO dto) {
        User user = userRepository.getOne(dto.getId());
        user.setName(dto.getName());
        user.setUserId(dto.getUserId());
        user.setDesignation(dto.getDesignation());
        user.setPhone(dto.getPhone());
        Role role = roleService.findRoleById(dto.getRoleId());
        user.setRole(role);
        return userRepository.save(user);
    }

    @Override
    public List<UserDTO> userList() {
        List<User> userList = userRepository.findAllByUsernameNotLike("admin");
        List<UserDTO> dtolist = new ArrayList<>();
        for (User user : userList) {
            if (!user.isDeleted()) {
                dtolist.add(userMapper.responseMapper(user));
            }
        }
        return dtolist;
    }

    @Override
    public void resetPassword(Long userId, String password) throws Exception {
        User user = userRepository.getOne(userId);
        user.setPassword(PasswordUtil.encryptPassword(password, PasswordUtil.EncType.BCRYPT_ENCODER, null));
        userRepository.save(user);
    }
}