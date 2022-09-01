package ru.kuzmina.DAO;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import ru.kuzmina.DAO.resultClasses.ProductInfoFromOrders;
import ru.kuzmina.EntityManagerUtils;
import ru.kuzmina.model.Order;
import ru.kuzmina.model.Product;
import ru.kuzmina.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public class OrderDao {
    EntityManagerUtils entityManagerUtils;
    EntityManager entityManager;

    public OrderDao(EntityManagerUtils entityManagerUtils) {
        this.entityManager = entityManagerUtils.getEntityManager();
    }

    public Optional<List<Order>> findAll() {
        return Optional.ofNullable(entityManager.createQuery("FROM Order").getResultList());
    }

    public Order findByID(Long orderId) {
        return entityManager.find(Order.class, orderId);
    }

    public Optional<List<Product>> findAllProductsInOrder(Long orderId) {
        return Optional.ofNullable(entityManager.createQuery("""
                SELECT o.product
                FROM Order o
                WHERE o.id = :orderId 
                """).setParameter("orderId", orderId).getResultList());
    }

    public Optional<List<User>> findAllUsersWhoBoughtProduct(Long ProductId) {
        return Optional.ofNullable(entityManager.createQuery("""
                SELECT o.user
                FROM Order o
                WHERE o.product.id = :ProductId 
                """).setParameter("orderId", ProductId).getResultList());
    }

    // возвращает только уникальные продукты с актуальными ценами
    public Optional<List<Product>> findAllProductsForUser(Long userId, String sortCondition) {
        return Optional.ofNullable(entityManager.createQuery("""
                        SELECT o.product
                        FROM Order o
                        WHERE user.id = :userId
                        ORDER BY :sortCondition 
                        """).setParameter("userId", userId).
                setParameter("sortCondition", sortCondition).getResultList());
    }

    //второй вариант с помощью вспомогательного класса для результатов
    //тут не получается, выводит только ид продуктов,
    //как организовать этот класс и как правильно написать запрос?
    //подозреваю, что имена полей и их порядок в выборке и классе должны совпадать?
    //и еще момент с сортировкой, в консоли если запустить этот запрос также с параметром, то все сортируется
    public Optional<List<ProductInfoFromOrders>> findAllProductsBoughtByUser(Long userId, String sortCondition) {
        return Optional.ofNullable(entityManager.createNativeQuery("""
                         SELECT o.product_id AS 'productId', 
                                P.title AS 'productTitle', 
                                o.price, 
                                o.quantity, 
                                o.id as 'orderID'
                         FROM Orders o
                         LEFT JOIN Products P ON P.id = o.product_id
                         WHERE o.user_id = :userId
                         ORDER BY :sortCondition
                        """, ProductInfoFromOrders.class)
                .setParameter("userId", userId)
                .setParameter("sortCondition", sortCondition)
                .getResultList());
    }

    //
    public Optional<List<Order>> findAllOrdersForUser(Long userId, String sortCondition) {
        return Optional.ofNullable(entityManager.createQuery("""
                        SELECT o
                        FROM Order o
                        WHERE user.id = :userId
                        ORDER BY :sortCondition 
                        """).setParameter("userId", userId).
                setParameter("sortCondition", sortCondition).getResultList());
    }


}


