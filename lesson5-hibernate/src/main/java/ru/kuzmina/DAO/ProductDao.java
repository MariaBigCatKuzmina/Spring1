package ru.kuzmina.DAO;

import jakarta.persistence.EntityManager;
import ru.kuzmina.model.Product;
import ru.kuzmina.model.User;

import java.util.List;
import java.util.Optional;

public class ProductDao {

    EntityManager entityManager;

    public ProductDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Optional<Product> findById(Long id) {
        return Optional.of(entityManager.find(Product.class, id));

    }

    public void deleteById(Long id) {
        entityManager.getTransaction().begin();
        try {
            findById(id).ifPresent(product ->
                    entityManager.
                            createQuery("DELETE FROM Product p WHERE p.id =" + id).
                            executeUpdate());
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.getTransaction().commit();
        }
    }

    public Optional<List<Product>> findALL() {
        return Optional.of(entityManager.createQuery("SELECT p FROM Product p").getResultList());
    }

    public Optional<List<User>> findUsersWhoBoughtProduct(Long productId){
        return Optional.ofNullable(entityManager.createQuery("""
                    SELECT p.users 
                    FROM Product p 
                    WHERE p.id = :id
                    """).setParameter("id", productId).getResultList());
    }

    public Optional<List<User>> findUsersWhoBoughtProductSQL(Long productId){
        return Optional.ofNullable(entityManager.createNativeQuery("""
                        SELECT u.*
                        FROM Products_Users pu
                        JOIN Users u ON pu.users_id = u.id
                        WHERE products_id = :id
                        """, User.class).
                setParameter("id", productId).getResultList());
    }


    public void save(Product product) {
        entityManager.getTransaction().begin();
        try {
            if (product.getId() != null) {
                entityManager.merge(product);
            } else {
                entityManager.persist(product);
            }
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.getTransaction().commit();
        }
    }
}
