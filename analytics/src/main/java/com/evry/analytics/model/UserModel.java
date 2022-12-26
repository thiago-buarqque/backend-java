package com.evry.analytics.model;

import com.evry.analytics.entity.User;
import com.evry.analytics.repository.EventRepository;
import com.evry.analytics.repository.UserRepository;

import org.springframework.stereotype.Component;

@Component
public class UserModel {

    public UserModel(
            EventRepository eventRepository, UserRepository userRepository) {

        _eventRepository = eventRepository;
        _userRepository = userRepository;
    }

    public User addUser(User user) {
        return _userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        _eventRepository.deleteByUserId(userId);
        _userRepository.deleteById(userId);
    }

    EventRepository _eventRepository;
    UserRepository _userRepository;

}