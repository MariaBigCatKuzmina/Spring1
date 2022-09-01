import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.cfg.Configuration;
import ru.kuzmina.DAO.ProductRepository;
import ru.kuzmina.model.Product;

public class Main {
    private static EntityManagerFactory entityManagerFactory;

    public static void init() {
        entityManagerFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
    }

    public static void shutDown() {
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }

    public static void main(String[] args) {
        init();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            ProductRepository productRepository = new ProductRepository(entityManager);

            productRepository.findById(1L).ifPresent(System.out::println);
            productRepository.findALL().ifPresent(System.out::println);

            productRepository.dropById(1L);
            productRepository.findALL().ifPresent(System.out::println);

            productRepository.save(new Product("product4", 23.12));
            productRepository.findALL().ifPresent(System.out::println);

            productRepository.deleteById(2L);
            productRepository.findALL().ifPresent(System.out::println);

            Product product = productRepository.findById(4L).get();
            product.setTitle("updated product");
            productRepository.save(product);
            productRepository.findALL().ifPresent(System.out::println);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
            shutDown();
        }
    }
}
