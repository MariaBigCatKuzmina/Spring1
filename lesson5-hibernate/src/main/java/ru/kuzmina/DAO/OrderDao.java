package ru.kuzmina.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import org.springframework.stereotype.Repository;
import ru.kuzmina.DAO.Dto.ProductInfoFromOrdersDto;
import ru.kuzmina.EntityManagerUtils;
import ru.kuzmina.model.Order;
import ru.kuzmina.model.Product;
import ru.kuzmina.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public class OrderDao {
    EntityManager entityManager;

    public OrderDao(EntityManagerUtils entityManagerUtils) {
        this.entityManager = entityManagerUtils.getEntityManager();
    }

    public List<Order> findAll() {
        return entityManager.createQuery("SELECT o FROM Order o").getResultList();
    }

    public Optional<Order> findByID(Long orderId) {
        return Optional.ofNullable(entityManager.find(Order.class, orderId));
    }

    public List<Product> findAllProductsInOrder(Long orderId) {
        return entityManager.createQuery("""
                SELECT o.product
                FROM Order o
                WHERE o.id = :orderId 
                """)
                .setParameter("orderId", orderId)
                .getResultList();
    }

    // возвращает только уникальные продукты с актуальными ценами
    public List<Product> findAllProductsForUser(Long userId, String sortCondition) {
        return entityManager.createQuery("""
                        SELECT o.product
                        FROM Order o
                        WHERE o.user.id = :userId
                        """, Product.class)
                .setParameter("userId", userId)
                .setParameter("sortCondition", sortCondition)
                .getResultList();
    }

    public List<User> findAllUsersWhoBoughtProduct(Long productId) {
        return entityManager.createQuery("""
                SELECT o.user
                FROM Order o
                WHERE o.product.id = :ProductId 
                """)
                .setParameter("productId", productId)
                .getResultList();
    }


    public List<ProductInfoFromOrdersDto> findAllProductsBoughtByUser(Long userId, String sortCondition) {
        return entityManager.createNativeQuery("""
                         SELECT o.product_id AS productId, 
                                p.title AS productTitle, 
                                o.price AS price, 
                                o.quantity as quantity, 
                                o.id as orderID
                         FROM Orders o
                         LEFT JOIN Products p ON p.id = o.product_id
                         WHERE o.user_id = :userId
                         ORDER BY :sortCondition
                        """, ProductInfoFromOrdersDto.class)
                .setParameter("userId", userId)
                .setParameter("sortCondition", sortCondition)
                .getResultList();
    }



    public List<Tuple> findAllProductsBoughtByUserTuple(Long userId, String sortCondition) {
        return entityManager.createNativeQuery("""
                         SELECT o.product_id AS productId, 
                                p.title AS productTitle, 
                                o.price as price, 
                                o.quantity as quantity, 
                                o.id as orderID
                         FROM Orders o
                         LEFT JOIN Products p ON p.id = o.product_id
                         WHERE o.user_id = :userId
                         ORDER BY :sortCondition
                        """, Tuple.class)
                .setParameter("userId", userId)
                .setParameter("sortCondition", sortCondition)
                .getResultList();
    }

    public List<Order> findAllOrdersForUser(Long userId, String sortCondition) {
        return entityManager.createQuery("""
                        SELECT o
                        FROM Order o
                        WHERE o.user.id = :userId
                        """, Order.class)
                .setParameter("userId", userId)
//                .setParameter("sortCondition", sortCondition)
                .getResultList();
    }


}


