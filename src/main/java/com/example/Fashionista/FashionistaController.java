package com.example.Fashionista;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FashionistaController {

    @Autowired
    private ProductRepository repository;

    @GetMapping
    public String home(Model model, HttpSession session) {
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        model.addAttribute("numberOfItemsInCart", cart == null ? 0 : cart.size());

        return "home";
    }

    @GetMapping("/products")
    public String products(Model model, HttpSession session,
                           @RequestParam(required = false, defaultValue = "") String category) {

        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");

        List<Product> products;
        if (category == null || category.isEmpty()) {
            products = repository.getProducts();
        } else {
            products = repository.getProductsByCategory(Category.valueOf(category));
        }

//        Product[][] productList = new Product[products.size() / 4 + 1][4];
//        for (int i = 0; i < products.size(); i++) {
//            productList[i / 4][i % 4] = products.get(i);
//        }
        List<List<Product>> productList = new ArrayList<>();
        List<Product> row = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            if (i%4 == 0) {
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
        model.addAttribute("numberOfItemsInCart", cart == null ? 0 : cart.size());

        return "products";
    }

    @GetMapping("/product")
    public String product(Model model, HttpSession session,
                          @RequestParam(required = false, defaultValue = "0") Long id,
                          @RequestParam(required = false, defaultValue = "0") int add) {

        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");

        boolean addedToCart = false;
        if (add > 0) {
            if (cart == null)
                cart = new ArrayList<>();
            cart.add(new CartItem(repository.getProduct(id), add));
            session.setAttribute("cart", cart);
            addedToCart = true;
        }

        model.addAttribute("product", repository.getProduct(id));
        model.addAttribute("addedToCart", addedToCart);
        model.addAttribute("numberOfItemsInCart", cart == null ? 0 : cart.size());

        return "product";
    }

    @GetMapping("/cart")
    public String cart(Model model, HttpSession session,
                       @RequestParam(required = false, defaultValue = "0") int row,
                       @RequestParam(required = false, defaultValue = "0") int remove) {

        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");

        if (remove > 0) {
            cart.remove(row);
        }

        double totalAmount = 0;
        if (cart != null) {
            for (CartItem item : cart) {
                totalAmount += item.product.price * item.quantity;
            }
        }
        model.addAttribute("totalAmount", Math.round(totalAmount * 100.0) / 100.0);
        model.addAttribute("numberOfItemsInCart", cart == null ? 0 : cart.size());

        return "cart";
    }

    @GetMapping("/checkout")
    public String checkout(Model model, HttpSession session) {

        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");

        double totalAmount = 0;
        if (cart != null) {
            for (CartItem item : cart) {
                totalAmount += item.product.price * item.quantity;
            }
        }
        model.addAttribute("totalAmount", totalAmount);
        model.addAttribute("numberOfItemsInCart", cart == null ? 0 : cart.size());

        return "checkout";
    }
}
