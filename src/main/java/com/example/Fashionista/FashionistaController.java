package com.example.Fashionista;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FashionistaController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping
    public String home() {
        return "home";
    }

    @GetMapping("/products")
    public String products(Model model,
                           @RequestParam(required = false, defaultValue = "0") Long category) {

        List<Product> products;
        if (category == null || category == 0) {
            products = productRepository.getProducts();
        } else {
            products = productRepository.getProductsByCategory(category);
        }

        List<List<Product>> productList = new ArrayList<>();
        List<Product> row = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            if (i % 4 == 0) {
                row = new ArrayList<>();
            }
            row.add(products.get(i));
            if (i % 4 == 3) {
                productList.add(row);
            }
        }
        if (products.size() % 4 != 0) {
            productList.add(row);
        }

        model.addAttribute("productList", productList);

        return "products";
    }

    @GetMapping("/product")
    public String product(Model model, HttpSession session,
                          @RequestParam(required = false, defaultValue = "0") Long id,
                          @RequestParam(required = false, defaultValue = "0") int add) {

        Cart cart = (Cart) session.getAttribute("cart");

        boolean addedToCart = false;
        if (add > 0) {
            if (cart == null)
                cart = new Cart();
            cart.addItem(productRepository.getProduct(id), 1);
            session.setAttribute("cart", cart);
            addedToCart = true;
        }

        model.addAttribute("product", productRepository.getProduct(id));
        model.addAttribute("addedToCart", addedToCart);

        return "product";
    }

    @GetMapping("/cart")
    public String cart(Model model, HttpSession session,
                       @RequestParam(required = false, defaultValue = "0") int row,
                       @RequestParam(required = false, defaultValue = "0") int remove) {

        Cart cart = (Cart) session.getAttribute("cart");

        if (remove > 0) {
            cart.removeItem(row);
        }

        return "cart";
    }

    @GetMapping("/checkout")
    public String checkout(@ModelAttribute Customer customer) {
        return "checkout";
    }

    @PostMapping("/checkout")
    public String checkoutSubmit(HttpSession session, @ModelAttribute Customer customer) {
        Cart cart = (Cart) session.getAttribute("cart");

        orderRepository.saveOrder(customer, cart);

        session.setAttribute("cart", null);

        return "checkout-complete";
    }

    @GetMapping("/AdminPage")
    public String AdminPage(Model model) {
        model.addAttribute("orders",orderRepository.getOrders());
        return "AdminPage";
    }

}
