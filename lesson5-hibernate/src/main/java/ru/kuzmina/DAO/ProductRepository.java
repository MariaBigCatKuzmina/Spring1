package ru.kuzmina.DAO;

import jakarta.persistence.EntityManager;
import ru.kuzmina.model.Product;

import java.util.List;
import java.util.Optional;

public class ProductRepository {

    EntityManager entityManager;

    public ProductRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Optional<Product> findById(Long id) {
        return Optional.of(entityManager.find(Product.class, id));

    }

    public void dropById(Long id) {
        entityManager.getTransaction().begin();
        try {
            findById(id).ifPresent(product -> entityManager.remove(product));
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.getTransaction().commit();
        }
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
