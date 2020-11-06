package com.example.Fashionista;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

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

		Product product = repository.getProduct(1L);

		Assertions.assertEquals("Puff-sleeved sequined dress", product.name);
	}

	@Test
	void testGetProductsByCategory() {
		ProductRepository repository = new ProductRepository();

		List<Product> productsInCategory = repository.getProductsByCategory(Category.TSHIRTS);

		Assertions.assertEquals(Category.TSHIRTS, productsInCategory.get(0).category);
	}
}
