package ru.kuzmina;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/products")
public class GetProductsServlet extends HttpServlet {
    private static final List<Product> productList = new ArrayList();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().printf("<html><body>");
        for (Product product : productList) {
            resp.getWriter().printf("<h1>" + product.toString() + "</h1>");
        }

        resp.getWriter().printf("</body></html>");
    }

    @Override
    public void init() throws ServletException {
        for (int i = 0; i < 10; i++) {
            productList.add(new Product(i, "Product " + (i + 1), Math.random() * 100));
        }
    }
}

