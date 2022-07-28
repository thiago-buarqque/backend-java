package com.evry.analytics.model;

import com.evry.analytics.DTO.UserDTO;
import com.evry.analytics.entity.User;
import com.evry.analytics.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserModel {
    public User addUser(UserDTO userDTO) {
        return _userRepository.save(_objectMapper.convertValue(userDTO,
                User.class));
    }

    public void deleteUser(Long userId) {
        _userRepository.deleteById(userId);
    }

    @Autowired
    UserRepository _userRepository;

    @Autowired
    ObjectMapper _objectMapper;
}
