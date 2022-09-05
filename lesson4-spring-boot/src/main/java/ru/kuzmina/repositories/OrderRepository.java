package ru.kuzmina.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.kuzmina.DAO.Dto.ProductInfoFromOrdersDto;
import ru.kuzmina.model.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = """
            SELECT o.product_id AS 'productId', 
            p.title AS 'productTitle', 
            o.price, 
            o.quantity, 
            o.id as 'orderID'
            FROM orders o
            LEFT JOIN products p ON p.id = o.product_id
            WHERE o.user_id = :userId
            ORDER BY :sortCondition
            """, nativeQuery = true)
    List<ProductInfoFromOrdersDto> getAllBoughtProductsForUser(Long userId, String sortCondition);
}
