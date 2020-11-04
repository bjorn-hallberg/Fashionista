package com.example.Fashionista;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    List<Product> products = new ArrayList<>();

    public ProductRepository() {
        Product product = new Product(1L, "Puff-sleeved sequined dress", Category.DRESSES, 29.99);
    }
}
