package com.example.Fashionista;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class FashionistaApplicationTests {

    @Test
    void testGetProducts() {
        ProductRepository repository = new ProductRepository();

        List<Product> products = repository.getProducts();

        Assertions.assertNotNull(products);
    }

    @Test
    void testGetProductById() {
        ProductRepository repository = new ProductRepository();

        Product product = repository.getProduct(17L);

        Assertions.assertEquals("Cable-knit cardigan", product.name);
    }

    @Test
    void testGetProductsByCategory() {
        ProductRepository repository = new ProductRepository();

        List<Product> productsInCategory = repository.getProductsByCategory(Category.TSHIRTS);

        for (Product product : productsInCategory) {
            Assertions.assertEquals(Category.TSHIRTS, product.category);
        }
    }
}
