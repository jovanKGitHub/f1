package org.example.repository;

import org.example.model.User;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepository {
    private final Map<UUID, User> users = new ConcurrentHashMap<>();

    public Optional<User> findById(UUID id) {
        return Optional.ofNullable(users.get(id));
    }

    public User save(User user) {
        users.put(user.getId(), user);
        return user;
    }

    public User getOrCreate(UUID userId) {
        return users.computeIfAbsent(userId, User::new);
    }
}