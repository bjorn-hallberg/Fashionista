package com.example.Fashionista;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FashionistaController {

    @Autowired
    private ProductRepository repository;

    @GetMapping
    public String home() {
        return "home";
    }

    @GetMapping("/products")
    public String products(Model model) {
        model.addAttribute("products", repository.getProducts());

        return "products";
    }

    @GetMapping("/product")
    public String product(Model model, @RequestParam(required = false, defaultValue = "0") Long id) {
        model.addAttribute("product", repository.getProduct(id));

        return "product";
    }

    @GetMapping("/cart")
    public String cart(Model model) {
        //model.addAttribute("cartitems", cartitems);
        //model.addAttribute("totalamount", totalamount);

        return "cart";
    }
}
