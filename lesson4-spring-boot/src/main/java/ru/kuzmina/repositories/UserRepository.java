package ru.kuzmina.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.kuzmina.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByNameLike(String usernameFilter);

    List<User> findAllByNameLikeAndEmailLike(String usernameFilter, String emailFilter);

    @Query(value = """
            SELECT u.*
            FROM users u
            WHERE (:usernameFilter IS NULL OR u.name like :usernameFilter)
            """, nativeQuery = true)
    List<User> userByUsername(String usernameFilter);

    @Query(value = """
            SELECT u.*
            FROM users u
            WHERE (:usernameFilter IS NULL OR u.name like :usernameFilter)
            AND (:emailFilter IS NULL OR u.email like :emailFilter)
            """, nativeQuery = true)
    List<User> userByUsernameAndEmail(String usernameFilter, String emailFilter);
}
