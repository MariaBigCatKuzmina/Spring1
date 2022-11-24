package ru.kuzmina.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.kuzmina.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = """
            SELECT u.*
            FROM users u
            WHERE (:usernameFilter IS NULL OR u.username LIKE :usernameFilter)
            AND (:emailFilter IS NULL OR u.email LIKE :emailFilter)
            """, nativeQuery = true)
    List<User> getUserByUsernameAndEmail(String usernameFilter, String emailFilter);
//    List<User> findUserByUsernameAndEmail(String usernameFilter, String emailFilter);

    Optional<User> findUserByUsername(String username);
}
