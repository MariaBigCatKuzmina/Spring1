package ru.kuzmina.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.kuzmina.model.Dto.ProductInfoFromOrdersDto;
import ru.kuzmina.model.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = """
            SELECT o.product_id AS productId,
            p.title AS productTitle,
            o.price as price,
            o.quantity as quantity,
            o.id as orderID
            FROM orders o
            JOIN products p ON p.id = o.product_id
            WHERE o.user_id = :userId
            ORDER BY :sortCondition
            """, nativeQuery = true)
    List<ProductInfoFromOrdersDto> getAllBoughtProductsForUser(Long userId, String sortCondition);
}
