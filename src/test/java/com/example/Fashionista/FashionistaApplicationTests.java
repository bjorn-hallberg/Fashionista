package com.example.Fashionista;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class FashionistaApplicationTests {

    @Test
    void testGetProducts() {
        ProductRepository repository = new ProductRepository();

        List<Product> products = repository.getProducts();

        Assert.assertNotNull(products);
    }

    @Test
    void testGetProductById() {
        ProductRepository repository = new ProductRepository();

        Product product = repository.getProduct(17L);

        Assert.assertEquals("Cable-knit cardigan", product.name);
    }

/*
    @Test
    void testGetProductsByCategory() {
        ProductRepository repository = new ProductRepository();

        List<Product> productsInCategory = repository.getProductsByCategory(Category.TSHIRTS);

        for (Product product : productsInCategory) {
            Assert.assertEquals(Category.TSHIRTS, product.categoryId);
        }
    }
*/
}
