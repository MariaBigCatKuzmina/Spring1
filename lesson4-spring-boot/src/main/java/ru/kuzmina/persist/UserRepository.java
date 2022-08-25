package ru.kuzmina.persist;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserRepository {
    private Map<Long, User> userMap;
    private AtomicLong identity;

    @PostConstruct
    public void init() {
        userMap = new ConcurrentHashMap<>();
        identity = new AtomicLong(0);
        for (int i = 0; i < 4; i++) {
            this.insert(new User("User" + (i + 1)));
        }
    }

    public void insert(User user) {
        Long id = identity.incrementAndGet();
        user.setId(id);
        userMap.put(id, user);
    }

    public List<User> getAll() {
        return new ArrayList<>(userMap.values());
    }

    public User getById(Long id) {
        return userMap.get(id);
    }

    public void update(User user) {
        userMap.put(user.getId(), user);
    }

    public void dropById(Long id) {
        userMap.remove(id);
    }
}
