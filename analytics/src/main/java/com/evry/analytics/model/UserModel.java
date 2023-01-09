package com.evry.analytics.model;

import com.evry.analytics.entity.User;
import com.evry.analytics.repository.EventRepository;
import com.evry.analytics.repository.UserRepository;

import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class UserModel {

    public UserModel(EventRepository eventRepository, UserRepository userRepository) {

        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    private static UUID getUUIDFromString(String userId) {
        return UUID.fromString(userId);
    }

    public void deleteUser(String userId) {
        eventRepository.deleteByUserId(userId);
        userRepository.deleteById(getUUIDFromString(userId));
    }

    public Optional<User> getUser(String userId) {
        return userRepository.findById(getUUIDFromString(userId));
    }

    EventRepository eventRepository;
    UserRepository userRepository;
}
