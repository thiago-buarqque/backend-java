package com.evry.analytics.service;

import com.evry.analytics.entity.User;
import com.evry.analytics.repository.EventRepository;
import com.evry.analytics.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {
    public User addUser(User user) {
        return _userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        _eventRepository.deleteByUserId(userId);
        _userRepository.deleteById(userId);
    }

    @Autowired
    UserRepository _userRepository;

    @Autowired
    EventRepository _eventRepository;
}
