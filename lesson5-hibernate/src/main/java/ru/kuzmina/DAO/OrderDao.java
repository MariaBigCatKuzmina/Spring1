package ru.kuzmina.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import org.hibernate.annotations.NamedNativeQuery;
import org.springframework.stereotype.Repository;
import ru.kuzmina.DAO.Dto.ProductInfoFromOrdersDto;
import ru.kuzmina.DAO.Dto.ProductInfoFromOrdersDtoEntity;
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

    public List<Order> findAll() {
        return entityManager.createQuery("FROM Order").getResultList();
    }

    public Optional<Order> findByID(Long orderId) {
        return Optional.ofNullable(entityManager.find(Order.class, orderId));
    }

    public List<Product> findAllProductsInOrder(Long orderId) {
        return entityManager.createQuery("""
                SELECT o.product
                FROM Order o
                WHERE o.id = :orderId 
                """).setParameter("orderId", orderId).getResultList();
    }

    // возвращает только уникальные продукты с актуальными ценами
    public List<Product> findAllProductsForUser(Long userId, String sortCondition) {
        return entityManager.createQuery("""
                        SELECT o.product
                        FROM Order o
                        WHERE user.id = :userId
                        ORDER BY :sortCondition 
                        """).setParameter("userId", userId).
                setParameter("sortCondition", sortCondition).getResultList();
    }

    public List<User> findAllUsersWhoBoughtProduct(Long productId) {
        return entityManager.createQuery("""
                SELECT o.user
                FROM Order o
                WHERE o.product.id = :ProductId 
                """).setParameter("productId", productId).getResultList();
    }
    //второй вариант с помощью вспомогательного класса для результатов
    //тут не получается, выводит только ид продуктов,
    //как организовать этот класс и как правильно написать запрос?
    //подозреваю, что имена полей и их порядок в выборке и классе должны совпадать?
    //и еще момент с сортировкой, в консоли если запустить этот запрос также с параметром, то все сортируется
 //   @NamedNativeQuery()
    public List<ProductInfoFromOrdersDto> findAllProductsBoughtByUser(Long userId, String sortCondition) {
        return entityManager.createNativeQuery("""
                         SELECT o.product_id AS 'productId',
                                P.title AS 'productTitle',
                                o.price AS 'price',
                                o.quantity AS 'quantity',
                                o.id as 'orderID'
                         FROM Orders o
                         LEFT JOIN Products P ON P.id = o.product_id
                         WHERE o.user_id = :userId
                         ORDER BY :sortCondition
                        """, ProductInfoFromOrdersDto.class)
                .setParameter("userId", userId)
                .setParameter("sortCondition", sortCondition)
                .getResultList();
    }



    public List<Tuple> findAllProductsBoughtByUserTuple(Long userId, String sortCondition) {
        return entityManager.createNativeQuery("""
                         SELECT o.product_id AS 'productId', 
                                P.title AS 'productTitle', 
                                o.price, 
                                o.quantity, 
                                o.id as 'orderID'
                         FROM Orders o
                         LEFT JOIN Products P ON P.id = o.product_id
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
                        WHERE user.id = :userId
                        ORDER BY :sortCondition 
                        """)
                .setParameter("userId", userId)
                .setParameter("sortCondition", sortCondition).getResultList();
    }


}


