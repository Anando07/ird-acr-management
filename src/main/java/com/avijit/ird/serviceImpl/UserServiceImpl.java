package com.avijit.ird.serviceImpl;

import com.avijit.ird.common.LogStatus;
import com.avijit.ird.common.Util.PasswordUtil;
import com.avijit.ird.configuration.MySessionInfo;
import com.avijit.ird.domain.AuditLog;
import com.avijit.ird.domain.dto.UserDTO;
import com.avijit.ird.mapper.UserMapper;
import com.avijit.ird.common.Exception.UserNotFoundException;
import com.avijit.ird.domain.Role;
import com.avijit.ird.domain.User;
import com.avijit.ird.repository.AuditLogRepository;
import com.avijit.ird.repository.UserRepository;
import com.avijit.ird.service.RoleService;
import com.avijit.ird.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    RoleService roleService;
    @Autowired
    MySessionInfo sessionInfo;

    private UserRepository userRepository;
    @Autowired
    AuditLogRepository logRepository;

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
        User user = userRepository.save(userMapper.requestMapper(userRequestDto));
        //saving log
        logRepository.save(new AuditLog(sessionInfo.getCurrentUser().getName() + LogStatus.createUser + new Date()));
        return user;
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
        User temp = userRepository.save(user);
        logRepository.save(new AuditLog(sessionInfo.getCurrentUser().getName() + LogStatus.updateUser + new Date()));
        return temp;
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
        logRepository.save(new AuditLog(sessionInfo.getCurrentUser().getName() + LogStatus.resetPass + new Date()));
    }

    @Override
    public void resetPasswordManually(String username, String password) throws Exception {
        User user = userRepository.findByUsername(username);
        user.setPassword(PasswordUtil.encryptPassword(password, PasswordUtil.EncType.BCRYPT_ENCODER, null));
        userRepository.save(user);
    }
}