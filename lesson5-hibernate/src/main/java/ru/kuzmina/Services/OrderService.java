package ru.kuzmina.Services;

import org.springframework.stereotype.Service;
import ru.kuzmina.DAO.OrderDao;

@Service
public class OrderService {
    private final OrderDao orderDao;

    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    //первый вариант получение через список заказов
    public void showAllProductsForUser(Long userId, String sortCondition) {
        System.out.println("Список продуктов для пользователя: " + userId);
        orderDao.findAllOrdersForUser(userId, sortCondition).ifPresent(orders -> {
            orders.forEach(order -> {
                String info = order.getProduct().getTitle() +
                        ", " + order.getPrice() + "р. " +
                        order.getQuantity() + "шт.";
                System.out.println(info);});
        });
    }

    //второй вариант с помощью вспомогательного класса для результатов
    //тут не получается, выводит только ид продуктов,
    //как организовать этот класс и как правильно написать запрос?
    //подозреваю, что имена полей и их порядок в выборке и классе должны совпадать?
    public void showProductsForUser(Long userId, String sortCondition) {
        System.out.println("Список продуктов для пользователя: " + userId);
        orderDao.findAllProductsBoughtByUser(userId, sortCondition)
                .ifPresent(System.out::println);
    }

    public void showAllUsersWhoBoughtProduct(Long productId, String sortCondition) {

    }
}
