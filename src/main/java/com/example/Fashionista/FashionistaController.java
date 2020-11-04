package com.example.Fashionista;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FashionistaController {

    @GetMapping
    public String home() {
        return "home";
    }

    @GetMapping("/products")
    public String products(Model model) {
        //model.addAttribute("products", products);

        return "products";
    }

    @GetMapping("/product")
    public String product(Model model) {
        //model.addAttribute("product", product);

        return "product";
    }
}
