
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.kuzmina.EntityManagerUtils;
import ru.kuzmina.Services.OrderService;
import ru.kuzmina.config.ApplicationConfiguration;


public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        EntityManagerUtils entityManagerUtils = context.getBean(EntityManagerUtils.class);
        try {
            System.out.println();
            OrderService orderService = context.getBean(OrderService.class);
            //вариант из списка заказов
            System.out.println("вариант из списка заказов");
            orderService.showAllProductsForUser(1L, "product_id");
            //вариант с классом
            System.out.println();
            System.out.println("вариант с классом Tuple");
            orderService.showProductsForUserTulpeTest(1L, "product_id");
            //вариант с классом
            System.out.println();
            System.out.println("вариант с интерфейсом");
            orderService.showProductsForUser(1L, "product_id");
        } finally {
            entityManagerUtils.shutDown();
        }
    }
}
