
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.kuzmina.DAO.ProductDao;
import ru.kuzmina.EntityManagerUtils;
import ru.kuzmina.Services.OrderService;
import ru.kuzmina.config.ApplicationConfiguration;
import ru.kuzmina.model.Contact;
import ru.kuzmina.model.ContactType;
import ru.kuzmina.model.User;

import java.util.Arrays;


public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        EntityManagerUtils entityManagerUtils = context.getBean(EntityManagerUtils.class);
        try {
            ProductDao productDao = new ProductDao(entityManagerUtils.getEntityManager());
            System.out.println("JPQL");
            productDao.findUsersWhoBoughtProduct(1L).ifPresent(System.out::println);
            System.out.println();
            System.out.println("Native SQL");
            productDao.findUsersWhoBoughtProductSQL(1L).ifPresent(System.out::println);

            System.out.println();
            OrderService orderService = context.getBean(OrderService.class);
            //вариант из списка заказов
            System.out.println("вариант из списка заказов");
            orderService.showAllProductsForUser(1L, "product_id");
            //вариант с классом
            System.out.println();
            System.out.println("вариант с классом");
            orderService.showProductsForUser(1L, "product_id");
        } finally {
            entityManagerUtils.shutDown();
        }
    }
}
