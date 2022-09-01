package ru.kuzmina.DAO;

import jakarta.persistence.EntityManager;
import ru.kuzmina.model.Product;
import ru.kuzmina.model.User;

import java.util.List;
import java.util.Optional;

public class UserDao {
    EntityManager entityManager;

    public UserDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Optional<User> findById(Long id) {
        return Optional.of(entityManager.find(User.class, id));
    }

    public void deleteById(Long id) {
        entityManager.getTransaction().begin();
        try {
            findById(id).ifPresent(product ->
                    entityManager.
                            createQuery("DELETE FROM User u WHERE u.id =" + id).
                            executeUpdate());
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.getTransaction().commit();
        }
    }

    public Optional<List<Product>> findALL() {
        return Optional.of(entityManager.createQuery("FROM User").getResultList());
    }

    public Optional<List<Product>> findUsersWhoBoughtProduct(Long userId){
        return Optional.ofNullable(entityManager.createQuery("""
                    SELECT u.products 
                    FROM User u 
                    WHERE u.id = :id
                    """).setParameter("id", userId).getResultList());
    }

    public Optional<List<Product>> findUsersWhoBoughtProductSQL(Long userId){
        return Optional.ofNullable(entityManager.createNativeQuery("""
                        SELECT p.*
                        FROM Products_Users pu
                        JOIN Products p ON pu.users_id = p.id
                        WHERE products_id = :id
                        """, Product.class).
                setParameter("id", userId).getResultList());
    }


    public void save(User user) {
        entityManager.getTransaction().begin();
        try {
            if (user.getId() != null) {
                entityManager.merge(user);
            } else {
                entityManager.persist(user);
            }
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.getTransaction().commit();
        }
    }

}
