package ru.kuzmina.DAO;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import ru.kuzmina.model.Product;

import java.util.List;
import java.util.Optional;

//@Repository
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

    public List<Product> findALL() {
        return entityManager.createQuery("SELECT p FROM Product p").getResultList();
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
