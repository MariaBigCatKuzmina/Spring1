package ru.kuzmina.Services;

import org.springframework.stereotype.Service;
import ru.kuzmina.DAO.Dto.ProductInfoFromOrdersDto;
import ru.kuzmina.DAO.Dto.ProductInfoFromOrdersDtoEntity;
import ru.kuzmina.DAO.OrderDao;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderDao orderDao;

    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    //первый вариант получение через список заказов
    public void showAllProductsForUser(Long userId, String sortCondition) {
        System.out.println("Список продуктов для пользователя: " + userId);
        Optional.ofNullable(orderDao.findAllOrdersForUser(userId, sortCondition)).ifPresent(orders -> {
            orders.forEach(order -> {
                String info = order.getProduct().getTitle() +
                        ", " + order.getPrice() + "р. " +
                        order.getQuantity() + "шт.";
                System.out.println(info);
            });
        });
    }

    //второй вариант с помощью вспомогательного класса для результатов
    //снова не получается ни через интерфейс ни через класс реализующий этот интерфейс
    public void showProductsForUser(Long userId, String sortCondition) {
        System.out.println("Список продуктов для пользователя: " + userId);
        orderDao.findAllProductsBoughtByUser(userId, sortCondition).stream()
                .map(dto -> {
                    ProductInfoFromOrdersDto entity = new ProductInfoFromOrdersDto();
                    entity.setOrderId(dto.getOrderId());
                    entity.setProductId(dto.getProductId());
                    entity.setProductTitle(dto.getProductTitle());
                    entity.setPrice(dto.getPrice());
                    entity.setQuantity(dto.getQuantity());
                    return entity;
                })
                .forEach(System.out::println);

    }

    public void showProductsForUserTulpeTest(Long userId, String sortCondition) {
        System.out.println("Список продуктов для пользователя: " + userId);
        orderDao.findAllProductsBoughtByUserTuple(userId, sortCondition).
                stream().map(tuple -> {
                    ProductInfoFromOrdersDtoEntity dto = new ProductInfoFromOrdersDtoEntity();
                    dto.setProductId(tuple.get("productId", Long.class));
                    dto.setProductTitle(tuple.get("productTitle").toString());
                    dto.setPrice(tuple.get("price", BigDecimal.class).doubleValue());
                    dto.setQuantity(tuple.get("quantity", Long.class).intValue());
                    dto.setOrderId(tuple.get("orderId", Long.class));
                    return dto;
                }).forEach(System.out::println);

    }

    public void showAllUsersWhoBoughtProduct(Long productId, String sortCondition) {

    }
}
